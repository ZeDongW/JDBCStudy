package cn.zedongw.datapool.dbcp;

import org.junit.Test;

public class DBCPDemoTest {
    DBCPDemo dd = new DBCPDemo();
    @Test
    public void getDbcp() {
       dd.getDbcp();
    }

    @Test
    public void getDbcpProperties() {
        dd.getDbcpProperties();
    }
}