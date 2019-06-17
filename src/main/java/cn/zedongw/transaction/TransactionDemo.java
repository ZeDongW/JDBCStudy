package cn.zedongw.transaction;

import cn.zedongw.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * @Author ZeDongW
 * @ClassName TransactionDemo
 * @Description JDBC 事务
 * @Version 1.0
 * @date ：Created in 2019/6/5 0005 23:11
 * @modified By：
 */

public class TransactionDemo {
    Connection conn;
    PreparedStatement pstmt;
    Savepoint trans;

    public void transfer(){
        String sql_zs = "update counts set money = money - 1000 where name = '张三'";
        String sql_ls = "update counts set money = money + 1000 where name = '李四'";
        String sql_zs1 = "update counts set money = money + 500 where name = '张三'";
        String sql_ls1 = "update1 counts set money = money - 500 where name = '李四'";
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql_zs);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(sql_ls);
            pstmt.executeUpdate();

            trans = conn.setSavepoint();

            pstmt = conn.prepareStatement(sql_zs1);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(sql_ls1);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            try {
                conn.rollback(trans);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtil.dbClose(conn, pstmt, null);
            }
        }
    }
}
