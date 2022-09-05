package com.example.ktp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ktp.bean.entity.User;
import com.example.ktp.bean.pojo.UserLesson;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public User queryUser(String username, String password);

    public User getByUsername(String username);

    public int insetIntoTableUserLesson(UserLesson userLesson);

    int deleteLesson(Map<String,String> map);
}
