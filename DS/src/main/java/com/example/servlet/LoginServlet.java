package com.example.servlet;

import com.example.entity.User;
import com.example.service.BaseController;
import com.example.service.LoginService;
import com.example.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
//        获取数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        if (BaseController.getUser(req) != null) {
//            用户已经登录
            Map<String, String> map = new HashMap<>();
            map.put("result", "-1");
            resp.getWriter().write(new Gson().toJson(map));
        } else {
            //        写入数据response
            resp.getWriter().write(LoginService.findUser(user, req));
//        session记录
        }

    }

    public void destroy() {
    }
}
