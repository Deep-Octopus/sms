package com.example.ktp.enums;


import lombok.AllArgsConstructor;

/**
 * 相应码枚举
 */
@AllArgsConstructor
public enum CodeEnum {

    SUCCESS(200,"请求成功"),
    NOT_LOGIN(499,"未登录"),
    SER_ERR(501,"服务异常"),
    AUTH_ERR(502,"权限不足"),
    DB_ERR(503,"参数异常"),
    SYS_ERR(504,"系统异常"),
    TOKEN_EXPIRED(505,"Token已经过期"),
    TOKEN_ERR(506,"Token验证失败"),
    ALREADY_EXIST(1000,"已经存在");
    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
