package com.example.service;

import com.example.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class BaseController {

    /**
     * 当前账号常量
     */
    private static final String USER = "user";

    public static User getUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (User) session.getAttribute(USER);
    }

    public static void setUser(HttpServletRequest req,User user) {
        HttpSession session = req.getSession();
        if (user != null) {
            session.setAttribute(USER, user);
            //session过期时间设置，以秒为单位，即在没有活动30分钟后，session将失效
            session.setMaxInactiveInterval(30 * 60);
            System.out.println(session.getAttribute(USER));
        }
    }
    public static void removeUser(HttpServletRequest req,User user){
        HttpSession session = req.getSession();
        if (user != null) {
            session.removeAttribute(USER);
        }
    }
}
