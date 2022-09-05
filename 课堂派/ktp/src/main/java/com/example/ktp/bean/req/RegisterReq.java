package com.example.ktp.bean.req;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterReq {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String school;

    @NotBlank
    private String stuId;

    @NotBlank
    private String identity;

    private String head;
}
