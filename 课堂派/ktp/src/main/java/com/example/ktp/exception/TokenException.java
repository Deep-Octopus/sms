package com.example.ktp.exception;

import com.example.ktp.dto.Result;
import com.example.ktp.enums.CodeEnum;

public class TokenException extends Exception{

    private final Result<String> result;

    /**
     * 调用时可以在任何代码处直接throws这个Exception,
     * 都会统一被拦截,并封装好json返回给前台
     *
     */
    public TokenException(CodeEnum codeEnum) {
        this.result = Result.buildErr(codeEnum);
    }

    public TokenException(Integer code,String msg) {
        this.result = Result.buildErr(code,msg);
    }

    public Result getResult() {
        return result;
    }


}
