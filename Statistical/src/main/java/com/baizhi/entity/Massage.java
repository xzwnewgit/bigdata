package com.baizhi.entity;

public class Massage {
    private String time;
    private Integer definitecasenum;//确诊人数
    private Integer suspectednum;//疑似人数
    private Integer curenum;//治疗人数
    private Integer didenum; //死亡人数
    private  Integer sum;//确诊人数+死亡人数
    private  Integer corentdefinitecasenum;//当前确证人数

    public Massage() {
    }

    public Massage(String time, Integer definitecasenum, Integer suspectednum, Integer curenum, Integer didenum) {
        this.time = time;
        this.definitecasenum = definitecasenum;
        this.suspectednum = suspectednum;
        this.curenum = curenum;
        this.didenum = didenum;
    }

    public Massage(String time, Integer definitecasenum, Integer suspectednum, Integer curenum, Integer didenum, Integer sum) {
        this.time = time;
        this.definitecasenum = definitecasenum;
        this.suspectednum = suspectednum;
        this.curenum = curenum;
        this.didenum = didenum;
        this.sum = sum;
    }

    public Massage(String time, Integer definitecasenum, Integer suspectednum, Integer curenum, Integer didenum, Integer sum, Integer corentdefinitecasenum) {
        this.time = time;
        this.definitecasenum = definitecasenum;
        this.suspectednum = suspectednum;
        this.curenum = curenum;
        this.didenum = didenum;
        this.sum = sum;
        this.corentdefinitecasenum = corentdefinitecasenum;
    }

    public Integer getCorentdefinitecasenum() {
        return corentdefinitecasenum;
    }

    public void setCorentdefinitecasenum(Integer corentdefinitecasenum) {
        this.corentdefinitecasenum = corentdefinitecasenum;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getDefinitecasenum() {
        return definitecasenum;
    }

    public void setDefinitecasenum(Integer definitecasenum) {
        this.definitecasenum = definitecasenum;
    }

    public Integer getSuspectednum() {
        return suspectednum;
    }

    public void setSuspectednum(Integer suspectednum) {
        this.suspectednum = suspectednum;
    }

    public Integer getCurenum() {
        return curenum;
    }

    public void setCurenum(Integer curenum) {
        this.curenum = curenum;
    }

    public Integer getDidenum() {
        return didenum;
    }

    public void setDidenum(Integer didenum) {
        this.didenum = didenum;
    }

    @Override
    public String toString() {
        return "Massage{" +
                "time='" + time + '\'' +
                ", definitecasenum=" + definitecasenum +
                ", suspectednum=" + suspectednum +
                ", curenum=" + curenum +
                ", didenum=" + didenum +
                ", sum=" + sum +
                ", corentdefinitecasenum=" + corentdefinitecasenum +
                '}';
    }
}
