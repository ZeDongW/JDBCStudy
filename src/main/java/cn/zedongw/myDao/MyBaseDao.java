package cn.zedongw.myDao;

import cn.zedongw.util.JDBCUtil;

import java.sql.*;

/**
 * @Author ZeDongW
 * @ClassName MyBaseDao
 * @Description 自定义持久层框架
 * @Version 1.0
 * @date ：Created in 2019/6/17 0017 8:22
 * @modified By：
 */

public class MyBaseDao {

    Connection conn; //数据库连接对象
    PreparedStatement pstmt; //预编译对象
    ResultSet rs;

    public void update(String sql, Object[] paramsValues){
        try {
            //获取数据库连接对象
            conn = JDBCUtil.getConnection();
            //获取预编译对象
            pstmt = conn.prepareStatement(sql);
            //获取参数元数据
            ParameterMetaData p_metaData = pstmt.getParameterMetaData();
            //根据参数元数据获取参数个数
            int count = p_metaData.getParameterCount();
            //依次填充参数
            for (int i = 1; i <= count; i++){
                pstmt.setObject(i, paramsValues[i - 1]);
            }
            //执行更新
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, pstmt, rs);
        }
    }
}
