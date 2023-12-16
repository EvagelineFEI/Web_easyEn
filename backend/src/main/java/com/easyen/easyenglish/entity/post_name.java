package com.easyen.easyenglish.entity;

import lombok.Data;

import java.util.Date;

@Data
public class post_name {
    private Integer post_id;
    private String state;
    private Integer user_id;
    private String title;
    private String content;
    private boolean public_or;
    private Date time;

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPublic_or() {
        return public_or;
    }

    public void setPublic_or(boolean public_or) {
        this.public_or = public_or;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", post_id=").append(post_id);
        sb.append(", state=").append(state);
        sb.append(", user_id=").append(user_id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", public_or=").append(public_or);
        sb.append(", time=").append(time);
        sb.append("]");
        return sb.toString();
    }
}