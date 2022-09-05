package com.example.ktp.service.serviceImpl;

import com.example.ktp.bean.entity.User;
import com.example.ktp.mapper.UserMapper;
import com.example.ktp.service.LoginService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User userLogin(User user) {
        return userMapper.queryUser(user.getUsername(),user.getPassword());
    }
}
