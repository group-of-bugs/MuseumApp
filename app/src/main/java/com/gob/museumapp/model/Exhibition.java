package com.gob.museumapp.model;

public class Exhibition {
    private Integer exhId;
    private Integer musId;
    private String exhName;
    private String musName;
    private String exhinfo;
    private String exhTime;
    private String imgUrl;

    public void setExhId(Integer exhId) {
        this.exhId = exhId;
    }

    public void setExhinfo(String exhinfo) {
        this.exhinfo = exhinfo;
    }

    public void setExhName(String exhName) {
        this.exhName = exhName;
    }

    public void setExhTime(String exhTime) {
        this.exhTime = exhTime;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setMusId(Integer musId) {
        this.musId = musId;
    }

    public void setMusName(String musName) {
        this.musName = musName;
    }

    public Integer getExhId() {
        return exhId;
    }

    public Integer getMusId() {
        return musId;
    }

    public String getExhinfo() {
        return exhinfo;
    }

    public String getExhName() {
        return exhName;
    }

    public String getExhTime() {
        return exhTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getMusName() {
        return musName;
    }
}
