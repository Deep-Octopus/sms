package com.example.ktp.controller;

import com.example.ktp.bean.req.RegisterReq;
import com.example.ktp.dto.Result;
import com.example.ktp.service.serviceImpl.RegisterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@CrossOrigin(allowCredentials = "true")
public class RegisterController {

    @Resource
    private RegisterServiceImpl registerService;

    @PostMapping("/register")
    public Result<Object> register(@RequestBody RegisterReq registerReq){
        return  registerService.userRegister(registerReq);
    }
}
