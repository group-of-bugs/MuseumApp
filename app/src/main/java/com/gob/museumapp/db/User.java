package com.gob.museumapp.db;

public class User {
    private int uid;
    private String phone;
    private String pwd;
    private String displayName;
    private String avatar;

    public int getUid() {
        return uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhone() {
        return phone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
