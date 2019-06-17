package cn.zedongw.bigNum;

import cn.zedongw.util.JDBCUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author ZeDongW
 * @ClassName ImgDemo
 * @Description 数据库存储二进制数据
 * @Version 1.0
 * @date ：Created in 2019/6/16 0016 16:14
 * @modified By：
 */

public class ImgDemo {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    /**
     * @Author: ZeDongW
     * @Description: 存储图片
     * @Date: 2019/6/16 0016 16:18
     * @Param: []
     * @return: void
     */
    public void saveImg(){
        String sql = "insert into bignum (img) values (?)";
        String img = "/1.jpg";
        InputStream in = null;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            in = ImgDemo.class.getResourceAsStream(img);
            pstmt.setBinaryStream(1,in);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } finally {
                    JDBCUtil.dbClose(conn, pstmt, rs);
                }
            }
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 获取图片
     * @Date: 2019/6/16 0016 16:18
     * @Param: []
     * @return: void
     */
    public void getImg(){
        String sql = "select * from bignum where id = ?";
        String img = "D:/1.jpg";
        BufferedOutputStream bf = null;
        InputStream in = null;
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,3);
            rs = pstmt.executeQuery();
            while (rs.next()){
                in = rs.getBinaryStream("img");
                bf = new BufferedOutputStream(new FileOutputStream(new File(img)));
                int len = 0;
                byte[] buf = new byte[1024*8];
                while ((len = in.read(buf)) != -1){
                    bf.write(buf,0,len);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (bf != null){
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } finally {
                    if (in != null){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }finally {
                            JDBCUtil.dbClose(conn, pstmt, rs);
                        }
                    }
                }
            }
        }
    }
}
