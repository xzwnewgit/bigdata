package com.baizhi.entity;

import java.io.Serializable;
import java.util.Arrays;

public class LoginSuccess implements Serializable {
    //登录时间戳
    private long currentTime ;
    //获取设备id
    private String equipementId;
    //获取用户id
    private String userId ;
    //获取登录的序列码：
    private String loginSequence ;
    //获取乱序密码
    private String password ;
    //获取登录区域
    private String region  ;
    //获取登录的时候的经纬度
    private Double[] geoPoint;
    //获取用户登录各个表单的用户输入所需时间
    private Double[] inputFeature ;//用户的输入时间特征
    private String userAgent ;

    public LoginSuccess() {
    }

    public LoginSuccess(long currentTime, String equipementId, String userId, String loginSequence, String password, String region, Double[] geoPoint, Double[] inputFeature, String userAgent) {
        this.currentTime = currentTime;
        this.equipementId = equipementId;
        this.userId = userId;
        this.loginSequence = loginSequence;
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

    public String getEquipementId() {
        return equipementId;
    }

    public void setEquipementId(String equipementId) {
        this.equipementId = equipementId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginSequence() {
        return loginSequence;
    }

    public void setLoginSequence(String loginSequence) {
        this.loginSequence = loginSequence;
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
        return "LoginSuccess{" +
                "currentTime=" + currentTime +
                ", equipementId='" + equipementId + '\'' +
                ", userId='" + userId + '\'' +
                ", loginSequence='" + loginSequence + '\'' +
                ", password='" + password + '\'' +
                ", region='" + region + '\'' +
                ", geoPoint=" + Arrays.toString(geoPoint) +
                ", inputFeature=" + Arrays.toString(inputFeature) +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
