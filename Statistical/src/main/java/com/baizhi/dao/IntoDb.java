package com.baizhi.dao;

import com.baizhi.entity.Into;
import com.baizhi.entity.Massage;
import com.baizhi.entity.Status;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntoDb {
    //添加数据
    public void insert(Into into);
    //------查询所有的数据
    public List<Into> selectMessage();
    //-------添加状态
    public void insertStatus(Status status);

    //------------查询所有状态
    public List<Status > selectStatus(String createtime);
    //---------------添加独立用户
    public void insertUser(User user);
    //-----------查询用户
    public List<User> selectUser(String time);
    //------------通过时间+状态查询
    public Status selectByTimeAndStatus(@Param("time") String time,
                                        @Param("status") String status);



    //---------------判断全部用户访问量，通过time查询
    public Into selectByTime(String time);

    //------------通过时间+ip查询
    public User selectByTimeAndIp(@Param("time") String time,
                                  @Param("ip") String ip);

    //---------------查询所有的病例----------
    public List<Massage> selectallMassage();
}
