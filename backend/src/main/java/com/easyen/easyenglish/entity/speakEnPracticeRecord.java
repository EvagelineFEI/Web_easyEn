package com.easyen.easyenglish.entity;
import lombok.Data;

import java.util.Date;
@Data
public class speakEnPracticeRecord {
    private Integer id;
    private Integer user_id;
    private String practiced_topic;
    private String topic_answer;
    private Date practice_date;
}
