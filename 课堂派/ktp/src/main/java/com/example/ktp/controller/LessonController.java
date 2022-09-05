package com.example.ktp.controller;


import com.example.ktp.bean.entity.Lesson;
import com.example.ktp.bean.pojo.UserLesson;
import com.example.ktp.dto.Result;
import com.example.ktp.service.LessonService;
import com.example.ktp.service.serviceImpl.LessonServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/lesson")
public class LessonController {

    @Resource
    private LessonServiceImpl lessonService;

    @PostMapping("/createLesson")
    public Result<Object> createLesson(@RequestBody Lesson lesson) {
        return lessonService.addLesson(lesson);
    }

//    @GetMapping("/init")
//    public Result<Object> initLesson(@RequestBody String username){
//
//
//    }

    @PostMapping("/joinLesson")
    public Result<Object> joinLesson(@RequestBody UserLesson userLesson) {

        return lessonService.joinLesson(userLesson);
    }

    @PostMapping("/initLesson")
    public Result<Map<String, List<Lesson>>> getLesson(@RequestBody String username){
        System.out.println(username);
        return lessonService.getLesson(username.replaceAll("\"",""));
    }

    @PostMapping("/setTop")
    public Result<Object> setTop(@RequestBody Map<String, String> map ){
        return lessonService.setTop(map);
    }

    @PostMapping("/dropOut")
    public Result<Object> dropOut(@RequestBody Map<String, String> map){
        return lessonService.dropOut(map);
    }

    @PostMapping("/deleteLesson")
    public Result<Object> deleteLesson(@RequestBody Map<String,String> map){
        return lessonService.deleteLesson(map);
    }
}
