package com.baizhi.service;

import java.util.List;
import java.util.Map;

public interface MessageService {
    //查询所有
    public List<Map<String,Object>> selectAll();
    //查询所有状态
    public List<Map<String,Object>> selectStatus(String createtime);
    //---------通过时间查询独立用户访问量
    public List<List<Object>> selectUser(String time);
    //查询所有病例
    public List<List<Object>> selectMassage();

}
