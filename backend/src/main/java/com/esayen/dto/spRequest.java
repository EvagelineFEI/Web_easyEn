package com.easyen.dto;

public class SpeakingPracticeRequest {
    private String topic;
    private String examType;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }
}
