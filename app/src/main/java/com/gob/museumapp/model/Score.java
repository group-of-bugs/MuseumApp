package com.gob.museumapp.model;

public class Score {
    private int sId;
    private int userId;
    private Double colId;
    private Double score;

    public Double getColId() {
        return colId;
    }

    public void setColId(Double colId) {
        this.colId = colId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
