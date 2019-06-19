package cn.zedongw.datapool.c3p0;

import org.junit.Test;

import static org.junit.Assert.*;

public class C3p0DemoTest {
    C3p0Demo cd = new C3p0Demo();
    @Test
    public void getC3p0() {
        cd.getC3p0();
    }

    @Test
    public void getC3p0Xml() {
        cd.getC3p0Xml();
    }
}