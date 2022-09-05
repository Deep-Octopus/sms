package com.example.ktp.service;

import com.example.ktp.bean.req.RegisterReq;
import com.example.ktp.dto.Result;

public interface RegisterService {

    Result<Object> userRegister(RegisterReq registerReq);
}
