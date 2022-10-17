package com.example.servlet;

import com.example.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
//req{beginIndex,num}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String beginIndex = req.getParameter("beginIndex");
        String num = req.getParameter("num");

//        判断是否是查询全部
        if("all".equals(num)){
            num = StudentService.getNumOfStudent()+"";
        }

        try {
            resp.getWriter().write(StudentService.quireByIndex(beginIndex,num));;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
