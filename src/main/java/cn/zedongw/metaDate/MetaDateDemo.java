package cn.zedongw.metaDate;

import cn.zedongw.util.JDBCUtil;
;

import java.sql.*;

/**
 * @Author ZeDongW
 * @ClassName MetaDateDemo
 * @Description 元数据
 * @Version 1.0
 * @date ：Created in 2019/6/17 0017 7:54
 * @modified By：
 */

public class MetaDateDemo {

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    /**
     * @Author: ZeDongW
     * @Description: 获取数据库元数据
     * @Date: 2019/6/17 0017 7:55
     * @Param: []
     * @return: void
     */
    public void getDatebaseMetaData(){
        try {
            //获取连接
            conn = JDBCUtil.getConnection();
            //获取数据库元数据
            DatabaseMetaData metaData = conn.getMetaData();

            //获取数据库URL
            System.out.println(metaData.getURL());
            //获取连接用户名
            System.out.println(metaData.getUserName());
            //获取数据库产品名称
            System.out.println(metaData.getDatabaseProductName());
            //获取数据库版本
            System.out.println(metaData.getDatabaseProductVersion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, pstmt, rs);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 获取参数元数据
     * @Date: 2019/6/17 0017 8:01
     * @Param: []
     * @return: void
     */
    public void getParameterMetaData(){
        try {
            //获取数据库连接
            conn = JDBCUtil.getConnection();
            //SQL
            String sql = "select * from dept where deptid=? and deptName=?";
            //预编译SQL
            pstmt = conn.prepareStatement(sql);
            //获取参数元数据
            ParameterMetaData p_metaData = pstmt.getParameterMetaData();
            //获取参数个数
            int parameterCount = p_metaData.getParameterCount();
            System.out.println(parameterCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, pstmt, rs);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 获取结果元数据
     * @Date: 2019/6/17 0017 8:09
     * @Param: []
     * @return: void
     */
    public void getResultSetMetaData(){
        try {
            //获取数据库连接队形
            conn = JDBCUtil.getConnection();
            //SQL
            String sql = "select * from dept";
            //预编译SQL
            pstmt = conn.prepareStatement(sql);
            //执行查询SQL获取结果集
            rs = pstmt.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rs_metaData = rs.getMetaData();
            //遍历结果集，通过结果集元数据获取结果
            while (rs.next()){
                int columnCount = rs_metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++){
                    String columnName = rs_metaData.getColumnName(i);
                    Object columnValue = rs.getObject(columnName);
                    System.out.print(columnName + "=" + columnValue + "  ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, pstmt, rs);
        }
    }
}
