package com.gob.museumapp.model;

// 用户类
public class User {
    private int uid;
    private String phone;
    private String password;
    private String displayName;
    private String avatar;

    public User(){
        this.uid = 0;
        this.phone = null;
        this.password = null;
        this.displayName = null;
        this.avatar = null;
    }
    public User(int uid, String phone, String password, String displayName, String avatar){
        this.uid = uid;
        this.phone = phone;
        this.password = phone;
        this.displayName = displayName;
        this.avatar = avatar;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
