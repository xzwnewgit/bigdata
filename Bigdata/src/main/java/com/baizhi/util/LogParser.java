package com.baizhi.util;

import com.baizhi.entity.EvalueData;
import com.baizhi.entity.LoginSuccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    // 匹配提取 评估数据的正则表达式
    final static Pattern EVAL_PATTERN = Pattern.compile("^INFO\\s(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\s(.*)\\sEVALUATE\\s\\[(.+)\\]\\s([a-z0-9]{32})\\s\"(.*)\"\\s([a-z]+)\\s\"([0-9\\.,]+)\"\\s\\[([0-9\\.,]+)\\]\\s\"(.*)\"");
    final static Pattern SUCCESS_PATTERN = Pattern.compile("^INFO\\s(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\s(.*)\\sSUCCESS\\s\\[(.+)\\]\\s([a-z0-9]{32})\\s\"(.*)\"\\s([a-z]+)\\s\"([0-9\\.,]+)\"\\s\\[([0-9\\.,]+)\\]\\s\"(.*)\"");


    public static Boolean isLoginSuccess(String loginSuccessLog){
        //判断是不是登录成功的日志
        return SUCCESS_PATTERN.matcher(loginSuccessLog).matches();
    }

    public static Boolean isEvalue(String evalueLog){
        //判断是不是需要评估的日志
        return  EVAL_PATTERN.matcher(evalueLog).matches();
    }

    public static LoginSuccess parserLoginSuccessData(String loginSuccessLog) throws ParseException {
        Matcher matcher = SUCCESS_PATTERN.matcher(loginSuccessLog);
        boolean matches = matcher.matches();//正则中这句话必须加
        System.out.println(matcher);
        String time = matcher.group(1);
        //获取登录时间信息
        System.out.println("------------");
        long currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
        //获取设备id
        String equipementId = matcher.group(2);
        //获取用户id
        String userId = matcher.group(3);
        //获取登录的序列码：
        String loginSequence = matcher.group(4);
        //获取乱序密码
        String password = matcher.group(5);
        //获取登录区域
        String region = matcher.group(6);
        //获取登录的时候的经纬度
        String[] arr = matcher.group(7).split(",");
        Double[] geoPoint = {Double.parseDouble(arr[0]),Double.parseDouble(arr[1])};
        //获取用户登录各个表单的用户输入所需时间
        String[] arrFeature = matcher.group(8).split(",");
        Double[] inputFeature = new Double[arrFeature.length];//用户的输入时间特征
        for (int i = 0; i < arrFeature.length; i++) {
            inputFeature[i] = Double.parseDouble(arrFeature[i]);
        }
        String userAgent = matcher.group(9);

        //需要实体类做数据的封装
        return new LoginSuccess(currentTime,equipementId,userId,loginSequence,password,region,geoPoint,inputFeature,userAgent);
    //LoginSuccess{currentTime=1574662260000, equipementId='app1', userId='zhangsan01', loginSequence='6ebaf4ac780f40f486359f3ea6934620', password='123456bCA', region='beijing', geoPoint=[116.4, 39.5], inputFeature=[1000.0, 1300.0, 1000.0], userAgent='Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36'}
    }

    public static EvalueData parserEvaluateData(String evaluateLog) throws ParseException {
        Matcher matcher = EVAL_PATTERN.matcher(evaluateLog);
        boolean matches = matcher.matches();
        String time = matcher.group(1);
        //当前登录时间
        long currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
        //设备id
        String euipementId = matcher.group(2);
        //用户id
        String UserId = matcher.group(3);
        //登录唯一序列号
        String loginSequenc = matcher.group(4);
        //密码
        String password = matcher.group(5);
        //登录地区
        String region = matcher.group(6);
        //经纬度
        String[] arr = matcher.group(7).split(",");
        Double[] geoPoint = {Double.parseDouble(arr[0]),Double.parseDouble(arr[1])};
        //用户输入特征
        String[] arrInputFeature = matcher.group(8).split(",");
        Double [] inputFeature = new Double[arrInputFeature.length];
        for (int i = 0; i < arrInputFeature.length; i++) {
            inputFeature[i]=Double.parseDouble(arrInputFeature[i]);
        }//将字符串类型的特征转化为数值类型的
        String userAgent = matcher.group(9);
        return new EvalueData(currentTime,euipementId,UserId,loginSequenc,password,region,geoPoint,inputFeature,userAgent);

    }




}
