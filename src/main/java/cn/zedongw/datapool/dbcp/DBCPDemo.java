package cn.zedongw.datapool.dbcp;

import cn.zedongw.entity.Students;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @Author ZeDongW
 * @ClassName DBCPDemo
 * @Description DBCP连接池
 * @Version 1.0
 * @date ：Created in 2019/6/20 0020 7:04
 * @modified By：
 */

public class DBCPDemo {

    /**
     * @Author: ZeDongW
     * @Description: 应编码配置DBCP
     * @Date: 2019/6/20 0020 7:25
     * @Param: []
     * @return: void
     */
    public void getDbcp(){
        //获取DBCP连接池
        BasicDataSource dataSource = new BasicDataSource();
        //连接池基础参数配置
        //数据库驱动
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //数据库连接地址URL
        dataSource.setUrl("jdbc:mysql:///zedongw?serverTimezone=GMT");
        //数据库用户名
        dataSource.setUsername("zedongw");
        //数据库密码
        dataSource.setPassword("zedongw@mysql");
        //初始化连接数
        dataSource.setInitialSize(3);
        //最大连接数
        dataSource.setMaxActive(6);
        //最大空闲时间
        dataSource.setMaxIdle(3000);

        //DBUtils组件
        QueryRunner qr = new QueryRunner(dataSource);
        //sql
        String sql = "select * from students";
        //执行查询并返回结果
        List<Students> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<Students>(Students.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //打印返回结果
        System.out.println(list);
    }

    /**
     * @Author: ZeDongW
     * @Description: 通过配置文件配置DBCP
     * @Date: 2019/6/20 0020 7:26
     * @Param: []
     * @return: void
     */
    public void getDbcpProperties(){
        //DBCP配置文件路径
        String path = "/dbcp.properties";
        //配置文件对象
        Properties prop = new Properties();
        try {
            //获取文件流，加载配置文件
            prop.load(DBCPDemo.class.getResourceAsStream(path));
            //根据连接池配置文件获取连接池对象
            DataSource dataSource = BasicDataSourceFactory.createDataSource(prop);
            //DBUtils组件
            QueryRunner qr = new QueryRunner(dataSource);
            //sql
            String sql = "delete from students where id = ?";
            //执行更新
            int update = qr.update(sql, "12");
            System.out.println(update);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
