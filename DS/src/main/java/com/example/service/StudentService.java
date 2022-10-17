package com.example.service;

import com.example.annotation.TableName;
import com.example.db.Tool;
import com.example.annotation.Operate;
import com.example.entity.Student;
import com.example.informationHandler.InfoHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

/**
 * @author zj
 */
@SuppressWarnings("all")
public class StudentService {
    public static Student getStudent(HttpServletRequest req) throws Exception {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String telephoneNumber = req.getParameter("telephoneNumber");
        String specialize = req.getParameter("specialize");
        return new Student(id,name,gender,telephoneNumber,specialize);
    }
    /**
     * 增添
     */
    public static String add(Student student) {
        Map<String, String> map = new HashMap<>();
        try {
            if (UserService.createTable(student.getClass())) {

                Connection connection = Tool.getConnection();
//        获取表名
                String tableName = student.getClass().getAnnotation(TableName.class).value();
                String sqlValue = "insert into " + tableName + " value(?,?,?,?,?)";
                System.out.println(sqlValue);
                PreparedStatement ps = connection.prepareStatement(sqlValue);
                ps.setString(1, student.getId());
                ps.setString(2, student.getName());
                ps.setString(3, student.getGender());
                ps.setString(4, student.getTelephoneNumber());
                ps.setString(5, student.getSpecialize());

                map.put("result", ps.executeUpdate() == 1 ? "添加成功" : "该学号已经存在");

                Tool.close(connection, ps);
            } else {
                map.put("result", "表不存在");
            }

        } catch (Exception e) {
            map.put("result", "错误");
            e.printStackTrace();
        }
        return InfoHandler.toJSON(map);
    }

    /**
     * 删除
     */
    public static String delete(String idForDelete) {
        Map<String, String> map = new HashMap<>();
        try {
            Connection connection = Tool.getConnection();
//        根据学号删除学生
            String sqlValue = "delete from student where id =?";
            PreparedStatement ps = connection.prepareStatement(sqlValue);

            ps.setString(1, idForDelete);
            map.put("result", ps.executeUpdate() == 1 ? "删除成功" : "无此学生");
            Tool.close(connection, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return InfoHandler.toJSON(map);
    }

    /**
     * 更改
     */
    public static String update(Student student, String tagId) {
        Map<String, String> map = new HashMap<>();
        try {
            Connection connection = Tool.getConnection();
            String updateSql = "update student set id =?, name =?," +
                    "gender =?, telephoneNumber =?,specialize =? where id = ?";

            PreparedStatement ps = connection.prepareStatement(updateSql);
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getTelephoneNumber());
            ps.setString(5, student.getSpecialize());
            ps.setString(6, tagId);

            map.put("result", ps.executeUpdate() == 1 ? "修改成功" : "该学号已经存在");

            Tool.close(connection, ps);
        } catch (Exception e) {
            map.put("result", "错误");
            e.printStackTrace();
        }
        return InfoHandler.toJSON(map);

    }

    /**
     * 查询
     */
    public static String quire(String quiryType, String value, boolean isAccurate) {
//        找到的学生通过InfoHandler类存进ArrayList，最后转换为JSON字符串返回
        InfoHandler<Student> infoHandler = new InfoHandler<>();
        try {
            Connection connection = Tool.getConnection();
//           是否精确查找
            String quireSql;
            if (isAccurate) {
                quireSql = "select * from student where "+quiryType+" = ?";
            } else {
                quireSql = "select * from student where "+quiryType+" LIKE ?";
            }
            System.out.println(quireSql);

            PreparedStatement ps = connection.prepareStatement(quireSql);
            if (isAccurate)
                ps.setString(1,value);
            else
                ps.setString(1,"%"+value+"%");
            System.out.println(ps);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String telephoneNumber = rs.getString("telephoneNumber");
                String specialize = rs.getString("specialize");

                infoHandler.add(new Student(id,name,gender,telephoneNumber,specialize));
            }
            Tool.close(connection,ps,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(infoHandler.getList());
        return infoHandler.getJSON();
    }

    public static String quireByIndex(String beginIndex,String num) throws SQLException {
        Connection connection = Tool.getConnection();
        InfoHandler<Student> infoHandler = new InfoHandler<>();
        int numOfStudents = getNumOfStudent();
        if (Integer.parseInt(beginIndex)<=numOfStudents){
            if (Integer.parseInt(beginIndex)+Integer.parseInt(num)>numOfStudents){
                num = numOfStudents-Integer.parseInt(beginIndex)+"";
            }

            String sql = "select * from student LIMIT "+beginIndex+","+num;

            Statement stat = connection.createStatement();
            System.out.println(sql);
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String telephoneNumber = rs.getString("telephoneNumber");
                String specialize = rs.getString("specialize");

                infoHandler.add(new Student(id,name,gender,telephoneNumber,specialize));
            }
            Tool.close(connection,stat,rs);
        }else {
            System.out.println("没有这么多数据");
            return InfoHandler.toJSON(new InfoHandler<>().getJSON());
        }
        return infoHandler.getJSON();
    }

    public static int getNumOfStudent(){
        int numOfStudent = 0;
        try{
            Connection connection = Tool.getConnection();
            String sql = "select * from student";
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(sql);
//            将光标移到最后一行
            rs.last();
//            获取总行数
            numOfStudent = rs.getRow();
            Tool.close(connection,stat,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return numOfStudent;
    }
}

