package cn.zedongw.beanutils;

import cn.zedongw.entity.Admin;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ZeDongW
 * @ClassName BeanUtilsDemo
 * @Description BeanUtils
 * @Version 1.0
 * @date ：Created in 2019/6/17 0017 6:50
 * @modified By：
 */

public class BeanUtilsDemo {

    /**
     * @Author: ZeDongW
     * @Description: BeanUtils基本操作
     * @Date: 2019/6/17 0017 6:57
     * @Param: []
     * @return: void
     */
    public void demo() throws InvocationTargetException, IllegalAccessException {
        Admin ad = new Admin();
        //BeanUtils组件实现对象属性的拷贝
        BeanUtils.copyProperty(ad, "userName", "jack");
        BeanUtils.copyProperty(ad, "age", "19");
//        System.out.println(ad);
        Admin newAd = new Admin();
        //BeanUtils组件实现对象的拷贝
        BeanUtils.copyProperties(newAd, ad);
//        System.out.println(newAd);
        //BeanUtils组件将Map数据拷贝到对象中
        Admin adMap = new Admin();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "jack");
        map.put("age", "24");
        //注意： map中的key要与javabean的属性名一致
        BeanUtils.populate(adMap, map);
        System.out.println(adMap);
    }

    /**
     * @Author: ZeDongW
     * @Description: 自定义类型转换器
     * @Date: 2019/6/17 0017 7:08
     * @Param: []
     * @return: void
     */
    public void myConvertDemo() throws InvocationTargetException, IllegalAccessException {
        //模拟表单提交的数据
        String userName = "jack";
        String age = "20";
        String birth = null;

        Admin ad = new Admin();

        //自定义方式注册日期类型转换器
        ConvertUtils.register(new Converter() {
            @Override
            public Date convert(Class type, Object value) {
                if (!type.equals(Date.class))
                    return null;
                if (value == null || "".equals(value.toString().trim()))
                    return null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return sdf.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);

        //把表单数据封装到对象中
        BeanUtils.copyProperty(ad, "userName", userName);
        BeanUtils.copyProperty(ad, "age", age);
        BeanUtils.copyProperty(ad, "birth", birth);

        System.out.println(ad);
    }

    /**
     * @Author: ZeDongW
     * @Description: 自带日期类型转换器
     * @Date: 2019/6/17 0017 7:50
     * @Param: []
     * @return: void
     */
    public void convertDemo() throws InvocationTargetException, IllegalAccessException {
        //模拟表单提交的数据
        String userName = "jack";
        String age = "20";
        String birth = null;

        Admin ad = new Admin();

        //自带日期类型转换器
        ConvertUtils.register(new DateLocaleConverter(), Date.class);

        //把表单数据封装到对象中
        BeanUtils.copyProperty(ad, "userName", userName);
        BeanUtils.copyProperty(ad, "age", age);
        BeanUtils.copyProperty(ad, "birth", birth);

        System.out.println(ad);
    }

}
