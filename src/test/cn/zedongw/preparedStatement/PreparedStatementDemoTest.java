package cn.zedongw.preparedStatement;

import cn.zedongw.util.JDBCUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PreparedStatementDemoTest {
    PreparedStatementDemo preparedStatementDemo = new PreparedStatementDemo();

    @Before
    public void setUp() throws Exception {
        preparedStatementDemo.conn = JDBCUtil.getConnection();
    }

    @After
    public void tearDown() throws Exception {
        JDBCUtil.dbClose(preparedStatementDemo.conn, preparedStatementDemo.stmt, preparedStatementDemo.resul);
    }

    @Test
    public void add() {
        preparedStatementDemo.add();
    }


    @Test
    public void queryAll() {
        preparedStatementDemo.queryAll();
    }

    @Test
    public void update() {
        preparedStatementDemo.update();
    }

    @Test
    public void delete() {
        preparedStatementDemo.delete();
    }

    @Test
    public void batch() {
        preparedStatementDemo.batch();
    }
}