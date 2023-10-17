package bookmanager;

import bookmanager.bean.User;
import bookmanager.dao.UserDao;
import bookmanager.dao.UserDaoImpl;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

@WebServlet(name = "login", value = "/login")
public class ServletTest1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code1");

        HttpSession session = request.getSession();

        String codeGet = (String)session.getAttribute("code");
        session.removeAttribute("code");

        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectUser(userName);


        if (!code.equals(codeGet)) {
            // 验证码不匹配
            session.setAttribute("message", "验证码错误");
        } else if (userName.isEmpty() || password.isEmpty()) {
            // 输入为空的情况
            session.setAttribute("message", "输入为空！");
        } else if (user == null) {
            // 用户不存在的情况
            session.setAttribute("message", "用户不存在");
        } else if (user.getPassword().equals(password)) {
            // 登陆成功，设置用户对象到Session中
            session.setAttribute("user", user);
            response.sendRedirect("welcome.jsp");
            return; // 登陆成功后，直接返回
        } else {
            // 密码错误
            session.setAttribute("message", "密码错误");
        }

        response.sendRedirect("login.jsp");
    }
}



