package cn.zedongw.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Author ZeDongW
 * @ClassName JDBCUtil
 * @Description JDBC连接工具
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 13:52
 * @modified By：
 */

public class JDBCUtil {
    private static String DRIVER = null;
    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;

    private static Connection conn = null;

    static {
        try {
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getResourceAsStream("/db.properties"));
            DRIVER= properties.getProperty("DRIVER");
            URL= properties.getProperty("URL");
            USERNAME= properties.getProperty("USERNAME");
            PASSWORD= properties.getProperty("PASSWORD");
            Class.forName(DRIVER);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        System.out.println("成功获取连接");
        return conn;
    }

    public static void dbClose(Connection conn, Statement stmt, ResultSet resul){
        if (resul != null){
            try {
                resul.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        System.out.println("成功关闭连接");
    }

}
