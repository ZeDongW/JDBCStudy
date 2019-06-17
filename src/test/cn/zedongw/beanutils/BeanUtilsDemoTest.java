package cn.zedongw.beanutils;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class BeanUtilsDemoTest {

    BeanUtilsDemo bud = new BeanUtilsDemo();

    @Test
    public void demo() throws InvocationTargetException, IllegalAccessException {
        bud.demo();
    }

    @Test
    public void myConvertDemo() throws InvocationTargetException, IllegalAccessException {
        bud.myConvertDemo();
    }

    @Test
    public void convertDemo() throws InvocationTargetException, IllegalAccessException {
        bud.convertDemo();
    }
}