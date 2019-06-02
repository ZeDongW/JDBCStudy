package cn.zedongw.statment;

import cn.zedongw.util.JDBCUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatementDemoTest {
    StatementDemo statemtDemo = new StatementDemo();

    @Before
    public void setUp() throws Exception {
        statemtDemo.conn = JDBCUtil.getConnection();
        statemtDemo.stmt = statemtDemo.conn.createStatement();
    }

    @After
    public void tearDown() throws Exception {
        JDBCUtil.dbClose(statemtDemo.conn, statemtDemo.stmt, statemtDemo.resul);
    }

    @Test
    public void add() {
        statemtDemo.add();
    }


    @Test
    public void queryAll() {
        statemtDemo.queryAll();
    }

    @Test
    public void update() {
        statemtDemo.update();
    }

    @Test
    public void delete() {
        statemtDemo.delete();
    }
}