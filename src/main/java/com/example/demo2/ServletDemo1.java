package com.example.demo2;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class ServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        BufferedReader reader = request.getReader();
//        String line;
//        while((line = reader.readLine()) != null){
//            System.out.println(line);
//        }
//        if (username.equals("jack") && password.equals("123456")) {
//            response.getWriter().write("login success!");
//            System.out.println("username:" + username);
//            System.out.println("password:" + password);
//        }else {
//            response.getWriter().write("login fail");
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // 现在，requestBody 包含了请求体中的数据
        System.out.println("Request Body: " + requestBody);

        if ("jack".equals(username) && "123456".equals(password)) {
            response.getWriter().write("login success!");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
        } else {
            response.getWriter().write("login fail");
        }
    }

}
