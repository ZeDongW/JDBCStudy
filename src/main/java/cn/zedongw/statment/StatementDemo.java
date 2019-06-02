package cn.zedongw.statment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author ZeDongW
 * @ClassName StatementDemo
 * @Description jdbc statement
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 17:03
 * @modified By：
 */

public class StatementDemo {
    Connection conn;   //JDBC 连接
    Statement stmt;     //JDBC stmt；
    ResultSet resul;   //JDBC 查询返回结果集

    /**
     * @Author: ZeDongW
     * @Description: JDBC 非预编译新增
     * @Date: 2019/6/2 0002 17:10
     * @Param: []
     * @return: void
     */
    public void add (){
        try {
            String sql = "insert into students (name,age) values ('张三',23)";
            int count = stmt.executeUpdate(sql);
            System.out.println("影响了" + count + "行");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void queryAll(){
        try {
            String sql = "select * from students";
            resul = stmt.executeQuery(sql);
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
            String sql = "update students set name = '李四' , age=25";
            int count = stmt.executeUpdate(sql);
            System.out.println("影响了" + count + "行");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(){
        try {
            String sql = "delete from students where id =1";
            int count = stmt.executeUpdate(sql);
            System.out.println("影响了" + count + "行");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
