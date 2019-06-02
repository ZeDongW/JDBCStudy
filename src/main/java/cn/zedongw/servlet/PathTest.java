package cn.zedongw.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName PathTest
 * @Description 测试WEB项目路径问题
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 16:56
 * @modified By：
 */

public class PathTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*JDBCQuery jdbcQuery = new JDBCQuery();
        jdbcQuery.queryTest1();*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
