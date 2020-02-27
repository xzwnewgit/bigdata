package com.baizhi.controller;

import com.baizhi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/log")
public class MessageController {
    @Autowired
    private MessageService service;

    @RequestMapping("/showAll")
    @ResponseBody
    public List<Map<String, Object>> showAll() {
        List<Map<String, Object>> maps = service.selectAll();
        return maps;
    }
    //根据时间查询数据
    @RequestMapping("/showstatus")
    @ResponseBody
    public List<Map<String, Object>> showstatus(String createtime) {
        List<Map<String, Object>> maps = service.selectStatus(createtime);
        return maps;
    }

    //根据时间查询独立用户访问量
    @RequestMapping("/showUser")
    @ResponseBody
    public List<List<Object>> showUser(String createtime) {
        List<List<Object>> lists = service.selectUser(createtime);
        return lists;
    }

    //根据时间查询所有的病例
    @RequestMapping("/showMassage")
    @ResponseBody
    public List<List<Object>> showMassage() throws Exception{


        List<List<Object>> lists = service.selectMassage();


        return lists;
    }

}
