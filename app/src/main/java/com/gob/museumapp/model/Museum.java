package com.gob.museumapp.model;

import java.sql.Time;
 // 博物馆类
public class Museum {
    private int mid;
    private String museumName;
    private String info;
    private int rank;
    private float score;
    private float flow;
    private Time closeTime;
    private Time openTime;

    public Museum(){
        this.mid = 0;
        this.museumName = null;
        this.info = null;
        this.rank = 0;
        this.score = 0.0F;
        this.flow = 0.0F;
        this.closeTime = null;
        this.openTime = null;
    }

    public Museum(int mid, String museumName, String info, int rank, float score, float flow, Time closeTime, Time openTime){
        this.mid = mid;
        this.museumName = museumName;
        this.info = info;
        this.rank = rank;
        this.score = score;
        this.flow = flow;
        this.closeTime = closeTime;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }
}
