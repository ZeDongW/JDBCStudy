package cn.zedongw.preparedStatement;

import java.sql.*;

/**
 * @Author ZeDongW
 * @ClassName PrepareStatementDemo
 * @Description JDBC PrepareStatementDemo
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 17:34
 * @modified By：
 */

public class PreparedStatementDemo {
    Connection conn;
    PreparedStatement stmt;
    ResultSet resul;

    public void add(){
        try {
            String sql = "insert into students (name,age) values (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"张三");
            stmt.setInt(2,23);
            int count = stmt.executeUpdate();
            System.out.println("影响了" + count + "行");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void queryAll(){
        try {
            String sql = "select * from students";
            stmt = conn.prepareStatement(sql);
            resul = stmt.executeQuery();
            while (resul.next()){
                System.out.println(resul.getInt("id") + ", " + resul.getString(2) + ", " + resul.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void update(){
        try {
            String sql = "update students set name=?, age=? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"李四");
            stmt.setInt(2,25);
            stmt.setInt(3,2);
            int count = stmt.executeUpdate();
            System.out.println("影响了" + count + "行");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(){
        try {
            String sql = "delete from students where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,2);
            int count = stmt.executeUpdate();
            System.out.println("影响了" + count + "行");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
