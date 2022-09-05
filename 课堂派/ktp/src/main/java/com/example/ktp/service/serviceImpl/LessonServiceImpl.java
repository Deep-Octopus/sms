package com.example.ktp.service.serviceImpl;

import com.example.ktp.bean.entity.Lesson;
import com.example.ktp.bean.pojo.UserLesson;
import com.example.ktp.dto.Result;
import com.example.ktp.enums.CodeEnum;
import com.example.ktp.mapper.LessonMapper;
import com.example.ktp.mapper.UserMapper;
import com.example.ktp.service.LessonService;
import com.example.ktp.util.StringTools;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.util.*;

@Service
public class LessonServiceImpl implements LessonService {

    @Resource
    private LessonMapper lessonMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<Object> addLesson(Lesson lesson) {
        if (lessonMapper.queryByLessonCode(lesson.getLessonCode()) == null) {
            lesson.setLessonCode(StringTools.generateCode(6));
            UserLesson userLesson = new UserLesson();
            userLesson.setLessonCode(lesson.getLessonCode());
            userLesson.setIdentity("teacher");
            userLesson.setIsTop("false");
            userLesson.setUsername(lesson.getTeacher());
            if (lessonMapper.insert(lesson) == 1 && userMapper.insetIntoTableUserLesson(userLesson) == 1) {
//                找到课程信息并返回
                Lesson teachLesson = lessonMapper.queryByLessonCode(userLesson.getLessonCode());
                if (teachLesson != null) {
                    return Result.buildSuccess(teachLesson);
                } else {
                    return Result.buildErr(CodeEnum.DB_ERR);
                }
            }
            return Result.buildErr(CodeEnum.DB_ERR);
        }
        return Result.buildErr(CodeEnum.ALREADY_EXIST);
    }

    @Override
    public Result<Object> joinLesson(UserLesson userLesson) {
        Lesson qLesson = lessonMapper.queryByLessonCode(userLesson.getLessonCode());
//        课程存在
        if (qLesson != null) {
//            不是这个课的老师
            if (!Objects.equals(qLesson.getTeacher(), userLesson.getUsername())) {
                // 没有选择过
                if (lessonMapper.queryByLessonCodeAndUsername(userLesson) == null) {
                    userLesson.setIsTop("false");
                    if (userMapper.insetIntoTableUserLesson(userLesson) == 1) {
//                        作业未提交人数加一
                        if ("student".equals(userLesson.getIdentity())){

                            lessonMapper.upUnsubmitted(userLesson.getLessonCode());
                        }
                        lessonMapper.increaseNumOfLesson(userLesson.getLessonCode());
                        Lesson lesson = lessonMapper.queryByLessonCode(userLesson.getLessonCode());
                        if (lesson != null) {
                            return Result.buildSuccess(lesson);
                        } else {
                            return Result.buildErr(CodeEnum.DB_ERR);
                        }

                    } else {
                        return Result.buildErr(CodeEnum.DB_ERR);
                    }
                } else {
                    return Result.buildErr(1000, "您已经选择过该课程");
                }
            } else {
                return Result.buildErr(1000, "您是该课老师，不能加入该课");
            }
        } else {
            return Result.buildErr(507, "该课不存在");
        }
    }

    @Override
    public Result<Map<String, List<Lesson>>> getLesson(String username) {
        List<Lesson> teachList = lessonMapper.queryLessonByUsername(username,"teacher");
        List<Lesson> studyList = lessonMapper.queryLessonByUsername(username,"student");
        Map<String,List<Lesson>> map = new HashMap<>();
        map.put("teach",teachList);
        map.put("study",studyList);
        return Result.buildSuccess(map);
    }

    @Override
    public Result<Object> setTop(Map<String, String> map) {

        if (lessonMapper.setTop(map)==1){
            return Result.buildSuccess();
        }
        return Result.buildErr(CodeEnum.DB_ERR);
    }

    @Override
    public Result<Object> dropOut(Map<String, String> map) {
        if (lessonMapper.dropOut(map)==1){
            lessonMapper.reduceNumOfLesson(map.get("lessonCode"));
            lessonMapper.downUnsubmitted(map.get("lessonCode"));

            return Result.buildSuccess();
        }
        return Result.buildErr(CodeEnum.DB_ERR);
    }

    @Override
    public Result<Object> deleteLesson(Map<String, String> map) {
        if (lessonMapper.deleteLesson(map)==1){
            if (userMapper.deleteLesson(map)!=0){
                return Result.buildSuccess();
            }
        }
        return Result.buildErr(CodeEnum.DB_ERR);
    }
}


