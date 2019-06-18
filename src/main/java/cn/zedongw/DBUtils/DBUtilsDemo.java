package cn.zedongw.DBUtils;

import cn.zedongw.entity.Students;
import cn.zedongw.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author ZeDongW
 * @ClassName DBUtilsDemo
 * @Description DBUtils组件
 * @Version 1.0
 * @date ：Created in 2019/6/18 0018 20:40
 * @modified By：
 */

public class DBUtilsDemo {

    Connection conn;  //数据库连接对象

    /**
     * @Author: ZeDongW
     * @Description: 自定义结果集封装数据
     * @Date: 2019/6/18 0018 22:41
     * @Param: []
     * @return: void
     */
    public void myQuery(int id) {
        try {
            //获取数据库连接对象
            conn = JDBCUtil.getConnection();
            //sql
            String sql = "select * from students where id = ?";
            //获取DBUtil工具对象
            QueryRunner queryRunner = new QueryRunner();
            //查询，并封装自定义结果集
            Students stu = queryRunner.query(conn, sql, id, new ResultSetHandler<Students>() {

                @Override
                public Students handle(ResultSet rs) throws SQLException {
                    if (rs.next()) {
                        Students stu = new Students();
                        stu.setId(rs.getInt("id"));
                        stu.setName(rs.getString("name"));
                        stu.setAge(rs.getInt("age"));
                        return stu;
                    }
                    return null;
                }
            });

            System.out.println(stu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, null, null);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 使用组件提供的结果集对象封装数据
     * @Date: 2019/6/18 0018 22:46
     * @Param: [id]
     * @return: void
     */
    public void queryOne(int id){
        try {
            //获取数据库连接对象
            conn = JDBCUtil.getConnection();
            //sql
            String sql = "select * from students where id = ?";
            //获取DBUtil工具对象
            QueryRunner queryRunner = new QueryRunner();
            //查询，使用组件提供的结果集对象封装数据
            Students stu = queryRunner.query(conn, sql, id, new BeanHandler<Students>(Students.class));
            System.out.println(stu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, null, null);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 查询返回list集合，集合元素是指定的对象
     * @Date: 2019/6/18 0018 22:59
     * @Param: []
     * @return: void
     */
    public void queryMore(){
        try {
            //获取数据库连接对象
            conn = JDBCUtil.getConnection();
            //sql
            String sql = "select * from students";
            //获取DBUtil工具对象
            QueryRunner queryRunner = new QueryRunner();
            //查询，查询返回list集合，集合元素是指定的对象
            List<Students> stu = queryRunner.query(conn, sql, new BeanListHandler<Students>(Students.class));
            System.out.println(stu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, null, null);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 查询结果集封装
     * @Date: 2019/6/18 0018 23:02
     * @Param:
     * @return:
     */
    public void queryArray(){
        try {
            //获取数据库连接对象
            conn = JDBCUtil.getConnection();
            //sql
            String sql = "select * from students";
            //获取DBUtil工具对象
            QueryRunner queryRunner = new QueryRunner();
            //查询，查询结果集封装
            //查询返回结果记录的第一行，封装对对象数组, 即返回：Object[]
//            Object[] query = queryRunner.query(conn, sql, new ArrayHandler());
            //把查询的每一行都封装为对象数组，再添加到list集合中
//            List<Object[]> query = queryRunner.query(conn, sql, new ArrayListHandler());
            //查询返回结果记录的第一行的第一列  (在聚合函数统计的时候用)
//            Object query = queryRunner.query(conn, sql, new ScalarHandler<>());
            //查询返回结果的第一条记录封装为map
            Map<String, Object> query = queryRunner.query(conn, sql, new MapHandler());
            System.out.println(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, null, null);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 删除
     * @Date: 2019/6/18 0018 23:09
     * @Param: []
     * @return: void
     */
    public void delete (int id){
        try {
            //获取数据库连接对象
            conn = JDBCUtil.getConnection();
            //sql
            String sql = "delete from students where id = ?";
            //获取DBUtil工具对象
            QueryRunner queryRunner = new QueryRunner();
            //执行更新
            int update = queryRunner.update(conn, sql, id);
            System.out.println(update);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, null, null);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 新增
     * @Date: 2019/6/18 0018 23:13
     * @Param: []
     * @return: void
     */
    public void insert(){
        try {
            //获取数据库连接对象
            conn = JDBCUtil.getConnection();
            //sql
            String sql = "insert into students (name, age) values (?,?)";
            //获取DBUtil工具对象
            QueryRunner queryRunner = new QueryRunner();
            //执行更新
            int update = queryRunner.update(conn, sql, "王浩", "26");
            System.out.println(update);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, null, null);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 批量新增
     * @Date: 2019/6/18 0018 23:15
     * @Param: []
     * @return: void
     */
    public void batch(){
        try {
            //获取数据库连接对象
            conn = JDBCUtil.getConnection();
            //sql
            String sql = "insert into students (name, age) values (?,?)";
            //获取DBUtil工具对象
            QueryRunner queryRunner = new QueryRunner();
            //执行更新
            int[] batch = queryRunner.batch(conn, sql, new Object[][]{{"刘贺", "35"}, {"钟昊还", "18"}});
            System.out.println(batch);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.dbClose(conn, null, null);
        }
    }
}
