package com.example.demo1;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getServlet")
public class GetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String testName = request.getParameter("testname");

        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println("GET请求已处理");
        response.getWriter().println("testname 的值是：" + testName);
        System.out.println("GET请求已处理,testname 的值是：" + testName);
    }
}
