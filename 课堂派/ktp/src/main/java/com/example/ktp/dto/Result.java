package com.example.ktp.dto;

import com.example.ktp.enums.CodeEnum;
import lombok.Data;
import lombok.ToString;

/**
 * 统一返回数据
 * @param <T>
 */
@ToString
@Data
public class Result <T>{
    /**
     * 响应码
     */
    private Integer status = CodeEnum.SUCCESS.getCode();
    /**
     * 响应信息
     */
    private String msg = CodeEnum.SUCCESS.getMsg();
    /**
     * 响应数据
     */
    private T data;

    /**
     * 请求成功方法
     */
    public static <T> Result<T> buildSuccess(){
        return new Result<>();
    }
    public static <T> Result<T> buildSuccess(T t){
        Result<T> result = new Result<>();
        result.setData(t);
        return result;
    }
    /**
     * 请求失败方法
     */
    public static <T> Result<T> buildErr(CodeEnum codeEnum){
        Result<T> result = new Result<>();
        result.setStatus(codeEnum.getCode());
        result.setMsg(codeEnum.getMsg());
        return result;
    }

    public static <T> Result<T> buildErr(Integer code, String msg){
        Result<T> result = new Result<>();
        result.setStatus(code);
        result.setMsg(msg);
        return result;
    }
}
