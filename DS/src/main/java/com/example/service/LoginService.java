package com.example.service;

import com.example.entity.User;
import com.example.db.Tool;
import com.example.informationHandler.InfoHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginService {
    public static String findUser(User user, HttpServletRequest req){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            //        检查表是否存在，不存在就建表
            if (UserService.createTable(user.getClass())) {
                //        保存是否找到状态
                Map<String, String> map = new HashMap<>();
                connection = Tool.getConnection();
//            查找
                String usernameSql = "select * from user where username = ? and password = ?";
                ps = connection.prepareStatement(usernameSql);

                ps.setString(1,user.getUsername());
                ps.setString(2,user.getPassword());
//             找到了？
                map.put("result",ps.executeQuery().next()?"1":"0");
                if (map.get("result").equals("1")){
                    try {
                        BaseController.setUser(req, UserService.getUser(user.getUsername()));
                        System.out.println("记录了" + user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    map.put("nickname", Objects.requireNonNull(UserService.getUser(user.getUsername())).getNickname());
                }
                Tool.close(connection,ps);
                return InfoHandler.toJSON(map);
            }else {
                System.out.println("ERR :: 建表失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return InfoHandler.toJSON("0");
    }
}
