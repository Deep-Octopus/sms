package com.example.ktp.bean.entity;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Lesson {

    private int lid;

    private String semester;

    private String lessonName;

    private String lessonCode;

    private String grade;

    private String teacher;

    private int numOfStudent;

    private String principal;

    private String isTop;

}
