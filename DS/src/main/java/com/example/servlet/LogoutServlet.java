package com.example.servlet;

import com.example.informationHandler.InfoHandler;
import com.example.service.BaseController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BaseController.removeUser(req,BaseController.getUser(req));
        Map<String, String> map = new HashMap<>();
        if (BaseController.getUser(req)==null){
            map.put("result","1");
        }else {
            map.put("result","1");
        }
        resp.getWriter().write(InfoHandler.toJSON(map));
    }
}
