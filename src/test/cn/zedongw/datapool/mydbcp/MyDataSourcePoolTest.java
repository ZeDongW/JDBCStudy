package cn.zedongw.datapool.mydbcp;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class MyDataSourcePoolTest {

    MyDataSourcePool msp = new MyDataSourcePool();

    @Test
    public void getConnection() throws SQLException {
        System.out.println("当前连接数为" + msp.curr_count);
        Connection c1 = msp.getConnection();
        System.out.println("第一取连接，当前连接数为" + msp.curr_count);
        Connection c2 = msp.getConnection();
        System.out.println("第二次获取连接，当前连接数为" + msp.curr_count);
        Connection c3 = msp.getConnection();
        System.out.println("第三次获取连接，当前连接数为" + msp.curr_count);
        Connection c4 = msp.getConnection();
        System.out.println("第四次获取连接，当前连接数为" + msp.curr_count);
        Connection c5 = msp.getConnection();
        System.out.println("第五次获取连接，当前连接数为" + msp.curr_count);
        Connection c6 = msp.getConnection();
        System.out.println("第六次获取连接，当前连接数为" + msp.curr_count);
        c6.close();
        c5.close();
        c4.close();
        c3.close();
        c2.close();
        c1.close();
        Connection c7 = msp.getConnection();
        System.out.println("第七次获取连接，当前连接数为" + msp.curr_count);
    }
}