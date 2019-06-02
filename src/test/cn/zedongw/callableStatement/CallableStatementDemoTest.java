package cn.zedongw.callableStatement;

import cn.zedongw.util.JDBCUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CallableStatementDemoTest {

    CallableStatementDemo callableStatementDemo = new CallableStatementDemo();
    @Before
    public void setUp() throws Exception {
        callableStatementDemo.conn = JDBCUtil.getConnection();
    }

    @After
    public void tearDown() throws Exception {
        JDBCUtil.dbClose(callableStatementDemo.conn, callableStatementDemo.stmt, callableStatementDemo.resul);
    }

    @Test
    public void callProNo() {
        callableStatementDemo.callProNo();
    }

    @Test
    public void callProIn() {
        callableStatementDemo.callProIn();
    }

    @Test
    public void callProOut() {
        callableStatementDemo.callProOut();
    }

    @Test
    public void callProInOut() {
        callableStatementDemo.callProInOut();
    }
}