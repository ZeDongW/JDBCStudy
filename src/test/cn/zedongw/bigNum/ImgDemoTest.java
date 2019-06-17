package cn.zedongw.bigNum;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImgDemoTest {

    ImgDemo demo = new ImgDemo();

    @Test
    public void saveImg() {
        demo.saveImg();
    }

    @Test
    public void getImg() {
        demo.getImg();
    }
}