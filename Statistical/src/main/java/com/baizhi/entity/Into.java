package com.baizhi.entity;

import java.io.Serializable;

public class Into implements Serializable {
    private String time;
    private Integer sum;

    public Into() {
    }

    public Into(String time, Integer sum) {
        this.time = time;
        this.sum = sum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Into{" +
                "time='" + time + '\'' +
                ", sum=" + sum +
                '}';
    }

}
