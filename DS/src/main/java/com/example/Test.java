package com.example;

import com.example.entity.Student;
import com.example.entity.User;
import com.example.informationHandler.InfoHandler;
import com.example.service.StudentService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws SQLException {
        InfoHandler<User> info = new InfoHandler<>();
        info.add(new User("123","123"));
        System.out.println(info.getJSON());
        Map<String, String> map = new HashMap<>();
        map.put("aa","bb");
        System.out.println(InfoHandler.toJSON(map));
        StudentService.getNumOfStudent();
        System.out.println(StudentService.quireByIndex("0","6"));;
    }
}
