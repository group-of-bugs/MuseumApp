package com.gob.museumapp.model;

public class Comment {
    private Integer com_id;
    private Integer user_id;
    private Double col_id;
    private String content;
    private String user_name;

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setCol_id(Double col_id) {
        this.col_id = col_id;
    }

    public void setCom_id(Integer com_id) {
        this.com_id = com_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Double getCol_id() {
        return col_id;
    }
    public Integer getCom_id() {
        return com_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public Integer getUser_id() {
        return user_id;
    }
    public String getContent() {
        return content;
    }
}