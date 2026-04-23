package com.ryder.springmall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class User {
    private Integer user_id;
    private String email;
    @JsonIgnore
    private  String password;
    private Date created_date;
    private Date last_modified_date;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return created_date;
    }

    public void setCreateDate(Date created_date) {
        this.created_date = created_date;
    }

    public Date getLastModifieDate() {
        return last_modified_date;
    }

    public void setLastModifieDate(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }
}
