package cn.zedongw.bigNum;

import org.junit.Test;

import static org.junit.Assert.*;

public class JDBCTextDemoTest {
    JDBCTextDemo demo = new JDBCTextDemo();
    @Test
    public void saveText() {
        demo.saveText();
    }

    @Test
    public void readText() {
        demo.readText();
    }
}