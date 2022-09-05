package com.example.ktp.service.serviceImpl;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.ktp.bean.entity.Homework;
import com.example.ktp.bean.pojo.TablePojo;
import com.example.ktp.bean.pojo.UserHomework;
import com.example.ktp.dto.Result;
import com.example.ktp.enums.CodeEnum;
import com.example.ktp.mapper.HomeworkMapper;
import com.example.ktp.mapper.LessonMapper;
import com.example.ktp.service.HomeworkService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Resource
    private HomeworkMapper homeworkMapper;

    @Resource
    private LessonMapper lessonMapper;


    @Override
    public Result<Object> postHomework(Homework homework) {
        if (homeworkMapper.insert(homework) == 1) {
            homework.setHid(homeworkMapper.getMaxHId());
            int numOfStu = lessonMapper.getNumOfStu(homework.getLessonCode());
            System.out.println(numOfStu);
            //            填充作业提交情况表
            if (homeworkMapper.setHomeworkStatus(homework.getLessonCode(),homework.getHid(),numOfStu)==1){
                return Result.buildSuccess(homework);
            }
        }
        return Result.buildErr(CodeEnum.DB_ERR);
    }

    @Override
    public Result<List<Homework>> initHomework(Map<String, String> map) {

        List<Homework> homeworkList = homeworkMapper.getHomeworkList(map);
        return Result.buildSuccess(homeworkList);
    }

    @Override
    public Result<Object> submitHomework(Map<String, String> map) {

            if (homeworkMapper.addSubmitHomework(map)==1){
                if (homeworkMapper.updateSubmit(Integer.parseInt(map.get("hid")))==1){
                    return Result.buildSuccess();
                }
            }
            return Result.buildErr(CodeEnum.DB_ERR);
        }

    @Override
    public Result<Object> queryHomework(Map<String, String> map) {
        UserHomework userHomework = homeworkMapper.queryStu(Integer.parseInt(map.get("hid")),map.get("username"));

        if (userHomework!=null){
            System.out.println("已经提交");
            return Result.buildSuccess(userHomework);
        }else {
            System.out.println("未提交");
            return Result.buildErr(404,"未提交");
        }
    }

    @Override
    public Result<List<TablePojo>> getHomeworks(Map<String, String> map) {
        return Result.buildSuccess(homeworkMapper.getHomeworks(Integer.parseInt(map.get("hid"))));
    }

    @Override
    public Result<Object> updateScore(UserHomework userHomework) {
        boolean flag = false;
        if ("未批".equals(homeworkMapper.queryScore(userHomework))){
            flag = true;
        }

        if (homeworkMapper.updateScore(userHomework)==1){
            if (flag){
                homeworkMapper.updateCorrect(userHomework);
            }
            return Result.buildSuccess();
        }
        return Result.buildErr(CodeEnum.DB_ERR);

    }
}
