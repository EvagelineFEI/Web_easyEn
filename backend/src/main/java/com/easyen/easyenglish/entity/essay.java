package com.easyen.easyenglish.entity;

import lombok.Data;

import java.util.Date;

// 作文表
@Data
public class essay {
    private Integer essay_id;
    private Integer user_id;
    private String essay_title;
    private String essay_content;
    private String essay_requirements;
    private Date upload_date;
    private String status;
}
