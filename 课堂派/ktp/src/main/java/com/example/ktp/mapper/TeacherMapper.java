package com.example.ktp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ktp.bean.entity.Teacher;
import com.example.ktp.bean.req.RegisterReq;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    public int insert(RegisterReq registerReq);


}
