package com.example.ktp.controller;

import com.example.ktp.bean.entity.Homework;
import com.example.ktp.bean.pojo.TablePojo;
import com.example.ktp.bean.pojo.UserHomework;
import com.example.ktp.dto.Result;
import com.example.ktp.service.serviceImpl.HomeworkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/homework")
public class HomeworkController {

    @Resource
    private HomeworkServiceImpl homeworkService;


    @PostMapping("/postHomework")
    public Result<Object> postHomework(@RequestBody Homework homework){
        return homeworkService.postHomework(homework);
    }

    @PostMapping("/initHomework")
    public Result<List<Homework>> initHomework(@RequestBody Map<String,String> map){
        return homeworkService.initHomework(map);
    }


    @PostMapping("/submitHomework")
    public Result<Object> submitHomework(@RequestBody Map<String,String> map){

        return homeworkService.submitHomework(map);
    }

    @PostMapping("/queryHomework")
    public Result<Object> queryHomework(@RequestBody Map<String, String> map){
        return homeworkService.queryHomework(map);
    }

    @PostMapping("/getHomeworks")
    public  Result<List<TablePojo>>  getHomeworks(@RequestBody Map<String,String> map){
        return homeworkService.getHomeworks(map);
    }

    @PostMapping("/updateScore")
    public Result<Object> updateScore(@RequestBody UserHomework userHomework) {
        return homeworkService.updateScore(userHomework);
    }
}
