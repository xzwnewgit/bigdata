package com.baizhi.entity;

import java.io.Serializable;

public class Status implements Serializable {
    private Integer id;
    private String status;
    private String createtime;
    private Integer sum;

    public Status() {
    }

    public Status(Integer id, String status, String createtime, Integer sum) {
        this.id = id;
        this.status = status;
        this.createtime = createtime;
        this.sum = sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", createtime='" + createtime + '\'' +
                ", sum=" + sum +
                '}';
    }
}
