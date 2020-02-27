package com.baizhi.service;

import com.baizhi.dao.IntoDb;
import com.baizhi.entity.Into;
import com.baizhi.entity.Massage;
import com.baizhi.entity.Status;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MessageServerImpl implements MessageService {
    @Autowired
    private IntoDb intoDb;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Map<String,Object>> selectAll() {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        List<Into> intos = intoDb.selectMessage();//获取数据库中所有的数据
        intos.forEach(statusInfo -> {
            HashMap map = new HashMap<String, Object>();
            map.put("name", statusInfo.getTime());//属性nane的值为时间
            map.put("y", statusInfo.getSum());//每天的访问量
            if ("2019-12-20".equals(statusInfo.getTime())) {
                map.put("sliced", true);
                map.put("selected", true);
            }
            result.add(map);
        });

        return result;
    }
        //查询所有状态
    @Override
    public List<Map<String, Object>> selectStatus(String createtime) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Status> statuses = intoDb.selectStatus(createtime);
        //获取数据库中所有的数据
        statuses.forEach(statusInfo -> {
            HashMap map = new HashMap<String, Object>();
            map.put("name", statusInfo.getStatus());//属性nane的值为时间
            map.put("y", statusInfo.getSum());//每天的访问量
            if ("200".equals(statusInfo.getStatus())) {
                map.put("sliced", true);
                map.put("selected", true);
            }
            result.add(map);
        });

        return result;
    }
    //---------通过时间查询独立用户访问量
    @Override
    public List<List<Object>> selectUser(String time) {
        List<List<Object>> result = new ArrayList<>();//存储数据的

        List<User> users = intoDb.selectUser(time);
        for (User user : users) {
            List<Object> list = new ArrayList<>();
            list.add(user.getIp());
            list.add(user.getNum());
            result.add(list);//将list添加到list中
        }

        return result;
    }
    //查询所有病例
    @Override
    public List<List<Object>> selectMassage() {
        List<List<Object>> result = new ArrayList<>();//存储数据的
        List<Massage> massages = intoDb.selectallMassage();//获取所有的信息

        ArrayList<Object> object1 = new ArrayList<>();
        for (Massage massage : massages) {
            object1.add(massage.getTime());//添加时间
        }

        ArrayList<Object> object2 = new ArrayList<>();
        for (Massage massage : massages) {
            object2.add(massage.getDefinitecasenum());//添加确诊人数
        }
        ArrayList<Object> object3 = new ArrayList<>();
        for (Massage massage : massages) {
            object3.add(massage.getSuspectednum());//添加疑似人数
        }
        ArrayList<Object> object4 = new ArrayList<>();
        for (Massage massage : massages) {
            object4.add(massage.getCurenum());//添加治愈人数
        }
        ArrayList<Object> object5 = new ArrayList<>();
        for (Massage massage : massages) {
            object5.add(massage.getDidenum());//添加死亡人数
        }
        ArrayList<Object> object6 = new ArrayList<>();
        for (Massage massage : massages) {
            object6.add(massage.getSum());//添加确证+疑似人数
        }

        ArrayList<Object> object7 = new ArrayList<>();
        for (Massage massage : massages) {
            object7.add(massage.getCorentdefinitecasenum());//添加确证+疑似人数
        }


        result.add(object1);
        result.add(object2);
        result.add(object3);
        result.add(object4);
        result.add(object5);
        result.add(object6);
        result.add(object7);
        return result;
    }

}
