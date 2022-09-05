package com.example.ktp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ktp.bean.entity.Student;
import com.example.ktp.bean.req.RegisterReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    public int insert(RegisterReq registerReq);

    public Student queryByStuId(String stuId);
}
