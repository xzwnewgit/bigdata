package com.baizhi.entity;

import java.io.Serializable;
import java.util.Arrays;

public class EvalueData implements Serializable {

    private long currentTime ;
    //设备id
    private String euipementId ;
    //用户id
   private String UserId ;
    //登录唯一序列号
    private String loginSequenc ;
    //密码
    private String password ;
    //登录地区
    private String region ;
    //经纬度
    private Double[] geoPoint ;
    //用户输入特征
    private Double [] inputFeature ;
    private String userAgent ;

    public EvalueData() {
    }

    public EvalueData(long currentTime, String euipementId, String userId, String loginSequenc, String password, String region, Double[] geoPoint, Double[] inputFeature, String userAgent) {
        this.currentTime = currentTime;
        this.euipementId = euipementId;
        UserId = userId;
        this.loginSequenc = loginSequenc;
        this.password = password;
        this.region = region;
        this.geoPoint = geoPoint;
        this.inputFeature = inputFeature;
        this.userAgent = userAgent;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public String getEuipementId() {
        return euipementId;
    }

    public void setEuipementId(String euipementId) {
        this.euipementId = euipementId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getLoginSequenc() {
        return loginSequenc;
    }

    public void setLoginSequenc(String loginSequenc) {
        this.loginSequenc = loginSequenc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double[] getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(Double[] geoPoint) {
        this.geoPoint = geoPoint;
    }

    public Double[] getInputFeature() {
        return inputFeature;
    }

    public void setInputFeature(Double[] inputFeature) {
        this.inputFeature = inputFeature;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "EvalueData{" +
                "currentTime=" + currentTime +
                ", euipementId='" + euipementId + '\'' +
                ", UserId='" + UserId + '\'' +
                ", loginSequenc='" + loginSequenc + '\'' +
                ", password='" + password + '\'' +
                ", region='" + region + '\'' +
                ", geoPoint=" + Arrays.toString(geoPoint) +
                ", inputFeature=" + Arrays.toString(inputFeature) +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
