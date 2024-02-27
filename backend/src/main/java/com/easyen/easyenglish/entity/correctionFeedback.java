package com.easyen.easyenglish.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// 批改建议表
@Data
@NoArgsConstructor
@AllArgsConstructor
public class correctionFeedback {
    private Integer feedback_id;
    private Integer essay_id;
    private Integer correction_score;
    private String correction_suggestions;
    private Date correction_date;
}
