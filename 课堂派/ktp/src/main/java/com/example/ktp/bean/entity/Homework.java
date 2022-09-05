package com.example.ktp.bean.entity;

import lombok.Data;

@Data
public class Homework {
    /**
     * id
     */
    private Integer hid;
    /**
     * 作业对应课堂码
     */
    private String lessonCode;
    /**
     * 作业题目
     */
    private String title;
    /**
     * 作业描述
     */
    private String homeworkDescription;
    /**
     * 作业类型
     */
    private String type;
    /**
     * 作业应用环节
     */
    private String applicationLink;
    /**
     * 作业所属章节
     */
    private String chapter;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 作业截止时间
     */
    private String endTime;
    /**
     * 作业总分
     */
    private Integer allScore;
    /**
     * 是否禁止超时提交
     */
    private String delivery;
    /**
     * 是否进行查重
     */
    private String duplicates;
    /**
     * 已经批改的
     */
    private Integer corrected;
    /**
     * 还未批改的
     */
    private Integer notCorrected;
    /**
     * 还未交的
     */
    private Integer unsubmitted;


}
