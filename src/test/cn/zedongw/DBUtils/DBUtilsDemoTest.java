package cn.zedongw.DBUtils;

import org.junit.Test;

import static org.junit.Assert.*;

public class DBUtilsDemoTest {
    DBUtilsDemo dd = new DBUtilsDemo();
    @Test
    public void myQuery() {
        dd.myQuery(15);
    }

    @Test
    public void queryOne() {
        dd.queryOne(15);
    }

    @Test
    public void queryMore() {
        dd.queryMore();
    }

    @Test
    public void queryArray() {
        dd.queryArray();
    }

    @Test
    public void delete() {
        dd.delete(13);
    }

    @Test
    public void insert() {
        dd.insert();
    }

    @Test
    public void batch() {
        dd.batch();
    }
}