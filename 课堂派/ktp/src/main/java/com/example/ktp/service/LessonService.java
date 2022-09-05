package com.example.ktp.service;

import com.example.ktp.bean.entity.Lesson;
import com.example.ktp.bean.pojo.UserLesson;
import com.example.ktp.dto.Result;

import java.util.Map;

public interface LessonService {

    Result<Object> addLesson(Lesson lesson);

    Result<Object> joinLesson(UserLesson userLesson);

    Object getLesson(String lessonCode);

    Result<Object> setTop(Map<String,String> map);

    Result<Object> dropOut(Map<String,String> map);

    Result<Object> deleteLesson(Map<String,String> map);
}
