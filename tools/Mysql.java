package tools;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

public class Mysql {
    private static Mysql instance;
    Connection conn = null;

    public static Mysql getInstance() {
        if (instance == null) {
            instance = new Mysql();
        }
        return instance;

    }

    public Connection getConnect() {
        if (conn == null) {
            conn = this.getnewConnect();
        }
        return conn;

    }


    private Connection getnewConnect() {
        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e1) {

            e1.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/birds"; // JDBC锟斤拷URL

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "root", "");

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public <T> List<T> getByID(T t, String id, String RoleID) {
        String s = t.toString().replace("Entity", "").replace("class domain.", "");
        System.out.println(t.toString());
        System.out.println(t);
        String sql = "select *from %s where %s = %s";
        String s1 = String.format(sql, s, id, RoleID);
        QueryRunner runner = new QueryRunner();
        List<T> query = null;
        try {
            System.out.println(s1);
            query = runner.query(this.getConnect(), s1, new BeanListHandler<T>((Class<T>) t));
        } catch (SQLException e) {
        /*    System.out.println("璇诲彇鏁版嵁搴撳紓甯�);
         * 
     
*/   System.out.println("数据库俩捏失败");
        	}
        return query;
    }

    public ResultSet getSkins(String openID) {
        // TODO Auto-generated method stub
        ResultSet set = null;
        try {
            Statement statement = this.getConnect().createStatement();
            set = statement.executeQuery("select SkinID from skin where UserID =" + openID);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return set;
    }

    public boolean updateBena(Object o) throws Exception {

        String userID = null;
        boolean run = false;
        Class<?> aClass = o.getClass();

        Field userIDs = aClass.getField("userid");
        if (userIDs != null) {
            userID = (String) userIDs.get(o);

        }
        if (userID != null) {
            StringBuilder sb = new StringBuilder("update ");
            sb.append(aClass.getSimpleName().replace("Entity", ""));
            sb.append(" set");

            for (Field field : aClass.getDeclaredFields()) {
                if (!field.getName().equalsIgnoreCase("userid")) {
                    Object o1 = field.get(o);
                    if (o1 != null && isnNotZero(o1)) {
                        if (!run)
                            run = true;
                        else sb.append(",");

                        sb.append(" " + field.getName() + "='" + o1+"'");
                    }
                }

            }
            if (run) {
                sb.append(" where userId='" + userID+"'");
                sb.append(";");
                System.out.println(sb.toString());
                return this.getConnect().createStatement().executeUpdate(sb.toString()) > 0;
            }
        }
        return false;
    }

    private boolean isnNotZero(Object o1) {
        if (o1.getClass().getSimpleName().equalsIgnoreCase("Integer")) {
            if ((Integer)o1 == 0) return false;
        }
        return true;

    }


    public ResultSet getRanking() {
        ResultSet resultSet = null;
        try {
            resultSet = this.getConnect().createStatement().executeQuery("SELECT * FROM  USER  ORDER BY score DESC ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}