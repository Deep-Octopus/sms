package com.example.servlet;

import com.example.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/quire")
public class QuireServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");

        String quireType = req.getParameter("type");
        String value = req.getParameter("value");
        boolean isAccurate = Boolean.getBoolean(req.getParameter("isAccurate"));

        resp.getWriter().write(StudentService.quire(quireType,value,isAccurate));

    }

    @Override
    public void destroy() {

    }
}
