package com.easyen.easyenglish.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// 作文表
@Data
@NoArgsConstructor
@AllArgsConstructor
public class essay {
    private Integer essay_id;
    private Integer user_id;
    private String essay_title;
    private String essay_content;
    private String essay_requirements;
    private Date upload_date;
    private String status;
}
