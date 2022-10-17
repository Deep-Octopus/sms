package com.example.service;

import com.example.db.Tool;
import com.example.annotation.TableName;
import com.example.annotation.Type;
import com.example.entity.User;
import com.example.informationHandler.InfoHandler;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ResourceBundle;


public class UserService {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("sms");
    public UserService() {
    }

    public static boolean createTable(Class<?> cls) throws SQLException {
        Connection connection = Tool.getConnection();
        StringBuilder sql = new StringBuilder("create table if not exists ");


        //获取类注解,并将类名作为表名（判断是否已经给定表名）
        TableName tn = cls.getAnnotation(TableName.class);
        if ("".equals(tn.value())) {
            sql.append(cls.getSimpleName()).append(" (");
        } else {
            sql.append(tn.value()).append(" (");
        }

        //获取全部属性

        Field[] fs = cls.getDeclaredFields();
        //遍历获取各属性的注解，并将值读取出来加入到sql语句
        for (Field f : fs
        ) {
            Type t = f.getAnnotation(Type.class);
            sql.append(t.name()).append(" ").append(t.type()).append(" ");
            if (!t.allowNull()) {
                sql.append("not null");
            }
            if (t.isKey()) {
                sql.append(" PRIMARY KEY ");
            }
            sql.append(",");
        }

        sql = new StringBuilder(sql.substring(0, sql.lastIndexOf(",")));
        sql.append(")");
        Statement stmt = null;
        try {
            Class.forName(BUNDLE.getString("driver"));

            stmt = connection.createStatement();

            stmt.execute(sql.toString());
            Tool.close(connection,stmt);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        Tool.close(connection,stmt);
        return false;
    }

    public static User getUser(String tagUsername) throws SQLException {
        Connection connection = Tool.getConnection();

        String sql = "select * from user where username = "+ tagUsername;
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()){
            String username = rs.getString("username");
            String nickname = rs.getString("nickname");
            String password = rs.getString("password");
            String email = rs.getString("email");

            return new User(username,nickname,email,password);
        }
        return null;
    }


    public static String changePassword(String email,String newPassword) throws SQLException {
        Connection connection = Tool.getConnection();
        String sql = "update user set password = ? where email = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,newPassword);
        ps.setString(2,email);
        if (ps.executeUpdate()!=0){
            Tool.close(connection,ps);
            return "修改成功";
        }else {
            Tool.close(connection,ps);
            return "修改失败，该邮箱地址不存在";
        }
    }
}
