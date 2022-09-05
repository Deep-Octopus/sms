package com.example.ktp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ktp.bean.entity.Lesson;
import com.example.ktp.bean.pojo.UserLesson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LessonMapper extends BaseMapper<Lesson> {

    int insert(Lesson lesson);

    Lesson queryByLessonCode(String lessonCode);

    UserLesson queryByLessonCodeAndUsername(UserLesson userLesson);

    List<Lesson> queryLessonByUsername(String username,String identity);

    int setTop(Map<String,String> map);

    int dropOut(Map<String, String> map);

    int increaseNumOfLesson(String lessonCode);

    int reduceNumOfLesson(String lessonCode);

    int deleteLesson(Map<String,String > map);

    int getNumOfStu(String lessonCode);

    int upUnsubmitted(String lessonCode);

    int downUnsubmitted(String lessonCode);
}
