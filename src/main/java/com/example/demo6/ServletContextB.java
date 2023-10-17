package com.example.demo6;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "ServletContextB", value = "/ServletContextB")
public class ServletContextB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object valueObject = getServletContext().getAttribute("username");

        if(valueObject!=null){
            response.getOutputStream().write(("从servletContext读取到的用户名数据："+ valueObject).getBytes());
        }
    }
}
