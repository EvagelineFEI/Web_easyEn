package com.easyen.easyenglish.entity;

import lombok.Data;

import java.util.Date;

// 批改建议表
@Data
public class correctionFeedback {
    private Integer feedback_id;
    private Integer essay_id;
    private Integer correction_score;
    private String correction_suggestions;
    private Date correction_date;
}
