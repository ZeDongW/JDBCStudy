package cn.zedongw.metaDate;

import org.junit.Test;

import static org.junit.Assert.*;

public class MetaDateDemoTest {

    MetaDateDemo mdd = new MetaDateDemo();

    @Test
    public void getDatebaseMetaData() {
        mdd.getDatebaseMetaData();
    }

    @Test
    public void getParameterMetaData() {
        mdd.getParameterMetaData();
    }

    @Test
    public void getResultSetMetaData() {
        mdd.getResultSetMetaData();
    }
}