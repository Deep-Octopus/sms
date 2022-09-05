package com.example.ktp.controller;

import com.example.ktp.dto.Result;
import com.example.ktp.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true")
public class UserController {

    @Resource
    private UserServiceImpl userService;


}
