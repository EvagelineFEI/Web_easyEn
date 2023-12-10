package com.easyen.easyenglish.dto;

public class topicReq {
    String Requirements;
    String Topic;
    public topicReq(String requirements, String topic){
        Requirements = requirements;
        Topic = topic;
    }

    public String getRequirements() {
        return Requirements;
    }

    public String getTopic() {
        return Topic;
    }
}
