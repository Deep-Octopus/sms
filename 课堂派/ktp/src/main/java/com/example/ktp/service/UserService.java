package com.example.ktp.service;

import com.example.ktp.bean.entity.User;
import com.example.ktp.dto.Result;

import java.util.Map;

public interface UserService {
    User findUser(User user);

    User getByUsername(String username);

}
