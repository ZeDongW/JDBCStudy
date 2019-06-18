package cn.zedongw.myDao;

import cn.zedongw.util.JDBCUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            //将参数传给SQL
            getSql(sql, paramsValues);
            //执行更新
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, pstmt, rs);
        }
    }

    public <T> List<T> query(String sql, Object[] paramsValues, Class clazz){
        //创建List对象用于保存结果集
        List<T> list = new ArrayList<>();
        //需要返回的对象
        T t = null;
        try {
            //将参数传给SQL
            getSql(sql, paramsValues);
            //执行查询
            rs = pstmt.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rs_metaData = rs.getMetaData();
            //获取结果集列数
            int count = rs_metaData.getColumnCount();
            //遍历结果集并封装对象
            while (rs.next()){
                t = (T) clazz.newInstance();
                for (int i = 0; i < count; i++){
                    String columnName = rs_metaData.getColumnName(i + 1);
                    Object columnValue = rs.getObject(columnName);
                    BeanUtils.copyProperty(t, columnName, columnValue);
                }
                list.add(t);
            }
            //返回list
            return list;
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, pstmt, rs);
        }
    }

    private void getSql(String sql, Object[] paramsValues) throws SQLException {
        //获取数据库连接对象
        conn = JDBCUtil.getConnection();
        //获取预编译对象
        pstmt = conn.prepareStatement(sql);
        //获取参数元数据
        ParameterMetaData p_metaData = pstmt.getParameterMetaData();
        //根据参数元数据获取参数个数
        int count = p_metaData.getParameterCount();
        //依次填充参数
        for (int i = 1; i <= count; i++) {
            pstmt.setObject(i, paramsValues[i - 1]);
        }
    }
}
