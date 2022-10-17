package com.example.servlet;

import com.example.entity.Student;
import com.example.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        System.out.println("add");
        Student student = null;
        try {
            student = StudentService.getStudent(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.getWriter().write(StudentService.add(student));
    }


    @Override
    public void destroy() {

    }
}
