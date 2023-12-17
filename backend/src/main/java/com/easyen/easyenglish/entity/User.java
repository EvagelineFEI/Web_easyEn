package com.easyen.easyenglish.entity;

public class User {

    private int user_id;
    private String username;
    private String password;
    private String email;
    private String other_info;
    private boolean account_lock_status;
    private int errors_count;
    private String userImg;
    public String getUser_name() {
        return username;
    }

    public void setUser_name(String user_name) {
        this.username = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOther_info() {
        return other_info;
    }

    public void setOther_info(String other_info) {
        this.other_info = other_info;
    }

    public boolean isAccountLockStatus() {
        return account_lock_status;
    }

    public void setAccountLockStatus(boolean account_lock_status) {
        this.account_lock_status = account_lock_status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getErrorsCount() {
        return errors_count;
    }

    public void setErrorsCount(int errors_count) {
        this.errors_count = errors_count;
    }


    public void setUserImg(String fileName) {
        this.userImg = fileName;
    }


}
