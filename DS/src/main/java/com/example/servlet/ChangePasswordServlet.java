package com.example.servlet;

import com.example.informationHandler.InfoHandler;
import com.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet("/change")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
//        获取session里面的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        删除session里面的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        Map<String, String> map = new HashMap<>();
        String email = req.getParameter("email");
        String newPassword = req.getParameter("newPwd");

//        用户输入的 验证码
        String vCode = req.getParameter("v_code");
        if (vCode != null && vCode.equalsIgnoreCase(token)){
            try {
                map.put("result",UserService.changePassword(email,newPassword));
                req.getSession().removeAttribute("user");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            map.put("result","验证码错误");
        }
        resp.getWriter().write(InfoHandler.toJSON(map));
    }
}
