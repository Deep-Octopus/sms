package com.example.db;


import com.example.service.StudentService;
import com.example.annotation.Operate;
import com.example.annotation.TableName;
import com.example.entity.Student;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author qp
 */
public class Tool {
    static ResourceBundle bundle = ResourceBundle.getBundle("sms");

    //    注册驱动
    static {
        try {
            Class.forName(bundle.getString("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //    得到数据库连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(bundle.getString("url-si"), bundle.getString("user"), bundle.getString("password"));
    }


    //    关闭资源
    public static void close(Connection conn, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    //    关闭打开的资源
    public static void close(Connection conn, Statement statement, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            close(conn, statement);
        }
    }

    public static String getClassName(Class<?> cl) {
        return cl.getSimpleName();
    }


//    public static void changeAnno(String methodName, String fieldName, String value) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
//        // 根据运行环境获取表名
//
//        String tableName = Student.class.getAnnotation(TableName.class).value();
//
//// 获取 Test 上的注解
//
//        Operate annoTable = StudentService.class.getDeclaredMethod(methodName).getAnnotation(Operate.class);
//
//        if (annoTable == null) {
//            throw new RuntimeException("please add @Table for Test");
//
//        }
//
//// 获取代理处理器
//
//        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annoTable);
//
//// 过去私有 memberValues 属性
//
//        Field f = invocationHandler.getClass().getDeclaredField("memberValues");
//
//        f.setAccessible(true);
//
//// 获取实例的属性map
//
//        Map memberValues = (Map) f.get(invocationHandler);
//
//// 修改属性值
//
//        memberValues.put(fieldName, value);
//    }


}


