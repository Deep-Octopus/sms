package com.example.ktp.controller;

import com.example.ktp.bean.annotations.PassToken;
import com.example.ktp.bean.entity.User;
import com.example.ktp.dto.Result;
import com.example.ktp.enums.CodeEnum;
import com.example.ktp.service.serviceImpl.LoginServiceImpl;
import com.example.ktp.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(allowCredentials = "true")
public class LoginController {

    @Resource
    private LoginServiceImpl loginService;

    @GetMapping("/")
    public Result<Object> init(){
        return Result.buildSuccess();
    }

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        User userInfo = loginService.userLogin(user);
        System.out.println(userInfo);
        if (userInfo != null) {
            String token = new JwtUtils().generateToken(user);
            Map<String,Object> map = new HashMap<>();
            map.put("userInfo",userInfo);
            map.put("token",token);
            return Result.buildSuccess(map);
        }

        return Result.buildErr(CodeEnum.DB_ERR);

   }
}
