package com.example.ktp.service.serviceImpl;


import com.example.ktp.bean.req.RegisterReq;
import com.example.ktp.dto.Result;
import com.example.ktp.enums.CodeEnum;
import com.example.ktp.mapper.StudentMapper;
import com.example.ktp.mapper.TeacherMapper;
import com.example.ktp.mapper.UserMapper;
import com.example.ktp.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Result<Object> userRegister(RegisterReq registerReq) {
        if (userMapper.getByUsername(registerReq.getUsername()) == null) {
            if (registerReq.getIdentity().equals("teacher")) {
                if (teacherMapper.insert(registerReq) == 1) {
                    return Result.buildSuccess();
                }
            } else if (registerReq.getIdentity().equals("student")) {
                if (studentMapper.queryByStuId(registerReq.getStuId()) == null) {
                    if (studentMapper.insert(registerReq) == 1) {
                        return Result.buildSuccess();
                    }
                }else
                    return Result.buildErr(409,"学号已经存在");
            }else
                return Result.buildErr(CodeEnum.DB_ERR);

        }
        return Result.buildErr(409,"用户名已经存在");
    }
}
