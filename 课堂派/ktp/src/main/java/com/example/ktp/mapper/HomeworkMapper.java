package com.example.ktp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ktp.bean.entity.Homework;
import com.example.ktp.bean.pojo.TablePojo;
import com.example.ktp.bean.pojo.UserHomework;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HomeworkMapper extends BaseMapper<Homework> {

    int insert(Homework homework);

    List<Homework> getHomeworkList(Map<String, String> map);

    int getMaxHId ();

    int addSubmitHomework(Map<String,String> map);

    int setHomeworkStatus(String lessonCode,int hid,int numOfStu);

    int updateSubmit(int hid);

    UserHomework queryStu(int hid, String username);

    List<TablePojo> getHomeworks(int hid);

    int updateScore(UserHomework userHomework);

    int updateCorrect(UserHomework userHomework);

    String queryScore(UserHomework userHomework);

}
