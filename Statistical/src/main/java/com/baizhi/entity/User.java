package com.baizhi.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String time;
    private Integer num;
    private String ip;

    public User(Integer id, String time, Integer num, String ip) {
        this.id = id;
        this.time = time;
        this.num = num;
        this.ip = ip;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", num=" + num +
                ", ip='" + ip + '\'' +
                '}';
    }
}
