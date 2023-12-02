package com.easyen.easyenglish.dto;

import java.util.ArrayList;
import java.util.List;

public class gptRequest {

    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    public gptRequest(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

    // getters and setters
    public String getModel(){
        return this.model;
    }

}
