package com.example.ktp.controller;


import com.example.ktp.dto.Result;
import com.example.ktp.enums.CodeEnum;
import com.example.ktp.exception.TokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 日志输出
 * 全局捕获异常
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.UNAUTHORIZED) //因为前后端分离 返回一个状态 一般是401 没有权限
    @ExceptionHandler(value =  TokenException.class)//捕获运行时异常ShiroException是大部分异常的父类
    public Result<Object> handler(TokenException e){
        log.error("权限不足异常：-----------------{}",e);
        return Result.buildErr(CodeEnum.AUTH_ERR);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST) //因为前后端分离 返回一个状态
    @ExceptionHandler(value =  RuntimeException.class)//捕获运行时异常
    public Result<Object> handler(RuntimeException e){
        log.error("运行时异常：-----------------{}",e);
        return Result.buildErr(402,e.getMessage());
    }
}
