package com.easyen.easyenglish.entity;

import lombok.Data;

import java.util.Date;

@Data
public class comments {
    private Integer comment_id;
    private boolean comment_state;
    private Integer user_id;
    private Integer post_id;
    private String contents;
    private Date comment_time;

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public boolean isComment_state() {
        return comment_state;
    }

    public void setComment_state(boolean comment_state) {
        this.comment_state = comment_state;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", comment_id=").append(comment_id);
        sb.append(", comment_state=").append(comment_state);
        sb.append(", user_id=").append(user_id);
        sb.append(", post_id=").append(post_id);
        sb.append(", contents=").append(contents);
        sb.append(", comment_time=").append(comment_time);
        sb.append("]");
        return sb.toString();
    }

}

