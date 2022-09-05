package com.example.ktp.service.serviceImpl;

import com.example.ktp.bean.entity.User;
import com.example.ktp.dto.Result;
import com.example.ktp.mapper.UserMapper;
import com.example.ktp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User findUser(User user) {
        return userMapper.queryUser(user.getUsername(),user.getPassword());
    }
    @Override
    public User getByUsername(String username){
        return userMapper.getByUsername(username);
    }


}
