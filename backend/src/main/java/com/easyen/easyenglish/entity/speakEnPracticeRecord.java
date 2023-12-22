package com.easyen.easyenglish.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class speakEnPracticeRecord{
    private Integer id;
    private Integer user_id;
    private String practiced_topic;
    private String topic_answer;
    private Date practice_date;
    private String requirements;
}
