/*
package tools;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class HeadlerUtil<T> {
    public static HeadlerUtil instance;

    public static HeadlerUtil getInstance() {
        if (instance == null)
            instance = new HeadlerUtil<>();
        return instance;
    }

    public List<T> Query(String sql, Class clazz) {
        if (sql == null) {

        }
        QueryRunner runner = new QueryRunner();

        try {
            Connection connect = Mysql.getInstance().getConnect();
      */
/*      BeanListHandler beanListHandler = new BeanListHandler<T>();*//*

            List<T> query = runner.query(connect, sql, new BeanListHandler<T>(clazz));

            return query;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
*/
