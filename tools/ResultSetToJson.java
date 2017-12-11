package tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetToJson {
    public static final JSONArray ResultSetToJSONArray(ResultSet rs) {
        JSONObject element = null;

        JSONArray ja = new JSONArray();
        ResultSetMetaData rsmd = null;
        String columnName, columnValue = null;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                element = new JSONObject();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i + 1);
                    columnValue = rs.getString(columnName);

                    try {
                        element.put(columnName, columnValue);
                        ja.put(element);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ja;
    }

    public static final JSONObject ResultSetToJSONObject(ResultSet rs) {
        JSONObject element = null;
        JSONArray ja = new JSONArray();
        JSONObject jo = new JSONObject();
        ResultSetMetaData rsmd = null;
        String columnName, columnValue = null;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                element = new JSONObject();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i + 1);
                    columnValue = rs.getString(columnName);
                    try {
                        element.put(columnName, columnValue);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                ja.put(element);
            }
            try {
                jo.put("result", ja);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jo;
    }

    public static final String ResultSetToJsonString(ResultSet rs) {
        return ResultSetToJSONObject(rs).toString();
    }
}
