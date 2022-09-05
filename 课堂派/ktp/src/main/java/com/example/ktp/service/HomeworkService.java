package com.example.ktp.service;

import com.example.ktp.bean.entity.Homework;
import com.example.ktp.bean.pojo.TablePojo;
import com.example.ktp.bean.pojo.UserHomework;
import com.example.ktp.dto.Result;

import java.util.List;
import java.util.Map;

public interface HomeworkService {

    Result<Object> postHomework(Homework homework);

    Result<List<Homework>> initHomework(Map<String, String> map);

    Result<Object> submitHomework(Map<String,String> map);

    Result<Object> queryHomework(Map<String,String> map);

    Result<List<TablePojo>>  getHomeworks(Map<String,String> map);

    Result<Object> updateScore(UserHomework userHomework);
}
