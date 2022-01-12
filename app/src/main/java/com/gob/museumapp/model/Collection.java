package com.gob.museumapp.model;

public class Collection {
    private Double colId;
    private Integer musId;
    private String colName;
    private String colEra;
    private String col_info;
    private String musName;
    private String imgUrl;
    private Double colScore;

    public Double getColId() {
        return colId;
    }

    public void setColId(Double colId) {
        this.colId = colId;
    }

    public Integer getMusId() {
        return musId;
    }

    public void setMusId(Integer musId) {
        this.musId = musId;
    }

    public String getCol_info() {
        return col_info;
    }

    public void setCol_info(String col_info) {
        this.col_info = col_info;
    }

    public String getColEra() {
        return colEra;
    }

    public void setColEra(String colEra) {
        this.colEra = colEra;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getMusName() {
        return musName;
    }

    public void setMusName(String musName) {
        this.musName = musName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getColScore() {
        return colScore;
    }

    public void setColScore(Double colScore) {
        this.colScore = colScore;
    }
}
