package com.example.demo3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
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

        System.out.println("Request Body: " + requestBody);
        String name = "jack";

        if ("jack".equals(username) && "123456".equals(password)) {
            request.setAttribute("name",name);
            request.getRequestDispatcher("/SuccessServlet").forward(request,response);
        } else {
            request.setAttribute("name",name);
            request.getRequestDispatcher("/FailServlet").forward(request,response);
        }

    }
}
