package cn.zedongw.bigNum;

import cn.zedongw.util.JDBCUtil;

import java.io.*;
import java.sql.*;

/**
 * @Author ZeDongW
 * @ClassName JDBCTextDemo
 * @Description 数据库存储文本
 * @Version 1.0
 * @date ：Created in 2019/6/16 0016 15:37
 * @modified By：
 */

public class JDBCTextDemo {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    /**
     * @Author: ZeDongW
     * @Description: 数据库存储文本
     * @Date: 2019/6/16 0016 16:15
     * @Param: []
     * @return: void
     */
    public void saveText(){
        String sql = "insert into bignum (text) values (?)";
        String file = "/db.properties";
        BufferedReader br = null;
        InputStream in = null;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            in = JDBCTextDemo.class.getResourceAsStream(file);
            br = new BufferedReader(new InputStreamReader(in));
            pstmt.setCharacterStream(1,br);
//            pstmt.setBinaryStream(1,in);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } finally {
                    JDBCUtil.dbClose(conn,pstmt,rs);
                }
            }
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 数据库读取文本
     * @Date: 2019/6/16 0016 16:16
     * @Param: []
     * @return: void
     */
    public void readText(){
        String sql = "select * from bignum where id = ?";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,2);
            rs = pstmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn,pstmt,rs);
        }
    }
}
