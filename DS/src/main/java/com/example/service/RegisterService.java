package com.example.service;

import com.example.db.Tool;
import com.example.entity.User;
import com.example.informationHandler.InfoHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegisterService {
    public static String addUser(User user) {
        try {
            //        检查表是否存在，不存在就建表
            if (UserService.createTable(user.getClass())) {
//            记录保存状态（0/1）
                Map<String, String> map = new HashMap<>();
                Connection connection = Tool.getConnection();
//                检查重复
                String repeatUsername = "select * from user where username = ?";
                String repeatEmail = "select * from user where email = ?";
                PreparedStatement ps1 = connection.prepareStatement(repeatUsername);
                PreparedStatement ps2 = connection.prepareStatement(repeatEmail);
                ps1.setString(1,user.getUsername());
                ps2.setString(1,user.getEmail());
                if (ps1.executeQuery().next()){
                    Tool.close(connection, ps1);
                    map.put("result","该用户名已经存在");
                    return InfoHandler.toJSON(map);
                }
                if (ps2.executeQuery().next()){
                    Tool.close(connection, ps2);
                    map.put("result","该邮箱已经被注册过");
                    return InfoHandler.toJSON(map);
                }
//                符合要求，加入数据库对应表
                String sql = "insert into user value(?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getNickname());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                map.put("result", ps.executeUpdate() != 0 ? "1" : "添加失败");
                Tool.close(connection, ps1);
                Tool.close(connection, ps2);
                Tool.close(connection, ps);
                return InfoHandler.toJSON(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return InfoHandler.toJSON("{result:表不存在}");
    }
}
