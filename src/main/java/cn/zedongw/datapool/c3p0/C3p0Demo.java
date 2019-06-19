package cn.zedongw.datapool.c3p0;

import cn.zedongw.entity.Students;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author ZeDongW
 * @ClassName C3p0Demo
 * @Description c3p0连接池
 * @Version 1.0
 * @date ：Created in 2019/6/20 0020 7:45
 * @modified By：
 */

public class C3p0Demo {

    public void getC3p0(){
        //创建c3p0连接池对象
        ComboPooledDataSource cpd = new ComboPooledDataSource();
        //配置数据库参数
        try {
            //配置连接池驱动
            cpd.setDriverClass("com.mysql.cj.jdbc.Driver");
            //数据库URL
            cpd.setJdbcUrl("jdbc:mysql:///zedongw?serverTimezone=GMT");
            //数据库用户名
            cpd.setUser("zedongw");
            //数据库密码
            cpd.setPassword("zedongw@mysql");
            //初始化连接数
            cpd.setInitialPoolSize(3);
            //最大连接数
            cpd.setMaxPoolSize(6);
            //空闲时间
            cpd.setMaxIdleTime(3000);

            //DBUtils组件
            QueryRunner qr = new QueryRunner(cpd);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @Author: ZeDongW
     * @Description: 通过配置文件自动加载配置
     * @Date: 2019/6/20 0020 7:58
     * @Param: []
     * @return: void
     */
    public void getC3p0Xml(){
        //获取连接池类
        ComboPooledDataSource cd = new ComboPooledDataSource();
        //DBUtils组件
        QueryRunner qr = new QueryRunner(cd);
        //sql
        String sql = "insert into students (name, age) values (?, ?)";
        //批量执行更新
        try {
            int[] batch = qr.batch(sql, new Object[][]{{"李四1", "21"}, {"李四2", "22"}, {"李四3", "23"}});
            System.out.println(batch);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
