package cn.zedongw.callableStatement;

import java.sql.*;

/**
 * @Author ZeDongW
 * @ClassName CallableStatementDemo
 * @Description JDBC CallableStatement
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 18:06
 * @modified By：
 */

public class CallableStatementDemo {
    Connection conn;
    CallableStatement stmt;
    ResultSet resul;

    public void callProNo(){
        try {
            String sql = "call Pro_no()";
            stmt = conn.prepareCall(sql);
            resul = stmt.executeQuery();
            while (resul.next()){
                System.out.println(resul.getInt("id") + ", " + resul.getString(2) + ", " + resul.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void callProIn(){
        try {
            String sql = "call Pro_in(?)";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1,3);
            resul = stmt.executeQuery();
            while (resul.next()){
                System.out.println(resul.getInt("id") + ", " + resul.getString(2) + ", " + resul.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void callProOut(){
        try {
            String sql = "call Pro_out(?)";
            stmt = conn.prepareCall(sql);
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.executeQuery();
            System.out.println(stmt.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void callProInOut(){
        try {
            String sql = "call Pro_inout(?,?)";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1,3);
            stmt.registerOutParameter(2, Types.VARCHAR);
            stmt.executeQuery();
            System.out.println(stmt.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
