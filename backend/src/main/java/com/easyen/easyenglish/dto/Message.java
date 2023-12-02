package com.easyen.easyenglish.dto;

public class Message {

    private String role;
    private String content;

    // constructor, getters and setters
    public Message(String r,String c){
     this.role = r;
     this.content = c;
    }
    public String getRole(){
        return this.role;
    }
    public String getContent(){
        return this.content;
    }
}
