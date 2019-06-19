package cn.zedongw.datapool.mydbcp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

/**
 * @Author ZeDongW
 * @ClassName MyDataSourcePool
 * @Description 自定义数据库连接池
 * @Version 1.0
 * @date ：Created in 2019/6/19 0019 14:53
 * @modified By：
 */

public class MyDataSourcePool {

    private static int init_size = 3;  //初始连接数
    public static int curr_count = 0; //当前连接数
    private static int max_count = 6; //最大连接数
    private static LinkedList<Connection> list = new LinkedList<Connection>(); //连接池集合

    /**
     * 静态代码块，初始化数据库连接池
     */
    static {
        for (int i = 0; i < init_size; i++){
            //初始化连接池，将连接对象放入list中
            list.addLast(createConnection());
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 创建数据库连接对象
     * @Date: 2019/6/19 0019 15:44
     * @Param: []
     * @return:
     */
    private static Connection createConnection(){
        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///zedongw?serverTimezone=GMT", "zedongw", "zedongw@mysql");
            //动态代理
            Connection proxy = (Connection) Proxy.newProxyInstance(
                    //获取类加载器
                    conn.getClass().getClassLoader(),
                    //目标对象实现的接口
                    new Class[]{Connection.class},
                    //事务处理器
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            //代理方法返回的值
                            Object result = null;
                            //获取当前方法的方法名
                            String methodName = method.getName();
                            //判断方法是否需要代理
                            if ("close".equals(methodName)) { //需要代理重写方法逻辑
                                if (list.size() < init_size) { //连接池连接数目未达到初始连接数
                                    list.addLast(conn); //将将连接对象放回连接池
                                    System.out.println("放入连接池，当前连接池连接数：" + list.size());
                                    System.out.println("当前连接数：" + curr_count);
                                } else { //连接池已装满，调用原本方法释放连接池
                                    result = method.invoke(conn, args);
                                    System.out.println("未放入连接池， 当前连接池连接数：" + list.size());
                                    System.out.println("Result:" + result + " 当前连接数：" + curr_count);
                                }
                            } else { //其他方法不代理，原路调用
                                result = method.invoke(conn, args);
                            }
                            return result;
                        }
                    }
            );
            curr_count++;
            return proxy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 获取数据库连接对象
     * @Date: 2019/6/19 0019 23:23
     * @Param: []
     * @return: java.sql.Connection
     */
    public Connection getConnection(){
        if (list.size() > 0){
            return list.removeLast();
        } else {
            if (curr_count >= max_count){
                throw new RuntimeException("当前数据库连接池已达到最大连接上限");
            } else {
                return createConnection();
            }
        }
    }
}
