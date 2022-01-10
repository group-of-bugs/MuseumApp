package com.gob.museumapp.model;

// 博物馆类
public class Museum {
    private int mid;
    private String museumName;
    private String imgUrl;
    private String address;
    private String remark;
    private String phone;
    private String master;
    private int rank;
    private float score;
    private float flow;
    private String openTime;
    private Double grade;

    public Museum(){
        this.mid = 0;
        this.museumName = null;
        this.rank = 0;
        this.score = 0.0F;
        this.flow = 0.0F;
        this.openTime = null;
    }

    public Museum(int mid, String museumName, int rank, float score, float flow, String openTime){
        this.mid = mid;
        this.museumName = museumName;
        this.rank = rank;
        this.score = score;
        this.flow = flow;
        this.openTime = openTime;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMuseumName() {
        return museumName;
    }

    public void setMuseumName(String museumName) {
        this.museumName = museumName;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getFlow() {
        return flow;
    }

    public void setFlow(float flow) {
        this.flow = flow;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
 }
