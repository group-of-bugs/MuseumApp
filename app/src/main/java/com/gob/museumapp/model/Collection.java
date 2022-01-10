package com.gob.museumapp.model;

public class Collection {
    private int colId;
    private int musId;
    private String colName;
    private String colEra;
    private String col_info;
    private String musName;
    private String imgUrl;


    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public int getMusId() {
        return musId;
    }

    public void setMusId(int musId) {
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
}
