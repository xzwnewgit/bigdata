import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class EvaluemodeTest {
    // 1--------登录城市----1,当前城市，2，历史城市， 返回值true有风险
    public Boolean evaluateLoginCity(String currentyCity, Set<String> historyCity){
        if(historyCity==null || historyCity.size()==0){
            return  false;
        }
        return  !historyCity.contains(currentyCity);//如果当前城市不存在历史记录城市中
    }

    //2--------------基于用户位移距离风险判定  //速度阈值
    //用户当前的登录时间 ，当前的经纬度信息
    public  Boolean evalueLoginAddress(long currentLoginTime,Double[] aPoint,long lastLongTime,Double[] bPoint,double speedThreadhold){
        //弧长距离的计算公式
        //地球半径 R =6371 km
        //将经纬度转化为弧长求弧度
        Double wA = aPoint[1]*Math.PI/180;
        Double wB = bPoint[1]*Math.PI/180;
        Double jA = aPoint[0]*Math.PI/180;
        Double jB = bPoint[0]*Math.PI/180;
        //拿到两点之间的距离
        Double distance= 6371*Math.acos(Math.cos(wA)* Math.cos(wB)*Math.cos(jB-jA)+Math.sin(wB)*Math.sin(wA));
        Double totalTime =  distance/speedThreadhold;//正常应该使用的时间(时速 500km/h)
       //转化为毫秒值，理论时间
        System.out.println("理论时长："+(totalTime ) +"小时");
        System.out.println("总距离:"+distance +"km");
        if((totalTime *3600*1000)>(currentLoginTime-lastLongTime)) {
            return  true;
        }else {
            return false;
        }

    }

    //3------------------频繁登录
    //参1：
    //参2：
    public Boolean evalueLohincount(Integer currentCounts,Integer threshold){
        if (currentCounts==null ||currentCounts==0){
            //没有登陆过
            return false;
        }else {
             return  currentCounts+1>threshold;
        }
    }

    //4-------------------登录设备发生变化
    //是否有风险？
    public Boolean evalueLoginDevice(String currentDevice,Set<String> historyDevice){
        if (historyDevice==null ||historyDevice.size()==0){
            return false;///没有风险
        }
        return ! historyDevice.contains(currentDevice);
    }
    //5---------------登录习惯发生变化 ,是否具有风险？
    //参1：当前的登录时间
    //参2：历史时间Set集合
    public Boolean evalueLoginTimehobby(long curentTime, Map<String,Map<String,Long>> histotyabt,Integer thredshold){
        if (histotyabt==null ||histotyabt.size()==0){
            //没有历史登录
            return  false;
        }
        //历史习惯时间的判断
        //从当前的时间中提取星期几和登录时间段

        String weeks[] ={"周日","周一","周二","周三","周四","周五","周六"};
        //日历
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTimeInMillis(curentTime);

        //从日历对象中抽取星期几
        //如果是1表示周天
        int dayofweek= calendar.get(7);//周几
        String strDayofWeek = weeks[dayofweek - 1];

        //获取登录时段
         //当天的时间17 表示时间 17点
         String houreofDay = calendar.get(11)+"";

         //当前的习惯记录中
        if (!histotyabt.containsKey(strDayofWeek)){ //例如周三用户没有登录
            //比对用户登录次数> 判定阈值
            Long loginTotalonweek = histotyabt
                    .entrySet()
                    .stream()
                    //转化为-->
                    //计算一天内的登录次数
                    .map(kv -> kv.getValue().entrySet().stream().map(kv2 -> kv2.getValue()).reduce((v1, v2) -> v1 + v2).get())
                    .reduce((v1, v2) -> v1 + v2).get();//得到一周总的登录次数
//拿到map<String,Long>  (15点， 次数2)
            return loginTotalonweek>thredshold;
        }

        //判定当前时段是否在习惯时间段内
        Map<String, Long> currentmap = histotyabt.get(strDayofWeek);//例如拿到当前周2的习惯数据

        //不包含
        if (!currentmap.containsKey(houreofDay)){
            return  true; //有风险
        }else {
            //计算出习惯数据，偶然数据
            //[1,3,3,5,9,10]
            List<Long> collect = currentmap
                    .entrySet()
                    .stream()
                    .map(kv -> kv.getValue())
                    //升序排列
                    .sorted()
                    .collect(Collectors.toList()); //返回每个时间段访问次数，按升序排列

            int thresholdcount  =(collect.size() * 2/ 4);//拿到3/4的数据

            //时段次数大于5.为习惯数据，小于5认为是的数据
            //获取判定数据为习惯数据还是偶然数据的阈值

            //计算阈值
            //习惯数据的时段集合
            List<String> hobby_hore = currentmap
                    .entrySet()
                    .stream()
                    //必须大于当前判定的阈值
                    .filter(kv -> kv.getValue() >= thresholdcount)
                    .map(kv -> kv.getKey())
                    .collect(Collectors.toList());
            System.out.println("习惯数据段;"+hobby_hore+"\t"+"判断是否为习惯数据的阈值："+thresholdcount);
            return  ! hobby_hore.contains(houreofDay);

        }


    }






    //--------------测试
    @Test
    public void t1(){

        //北京坐标： 116.40262116406248,39.90019910760629
        //上海;    121.48941,31.40527
        //当前登录时间 1582862400  2020年2月28日 12:00:00
        //上次登录时间：  1582855200       2020年2月28日 10:00:00
        //500km/h
        Boolean aBoolean = evalueLoginAddress(1582862400, new Double[]{116.40262116406248, 39.90019910760629}, 1582855200, new Double[]{121.48941, 31.40527}, 500);

        System.out.println("是否有风险:"+aBoolean);
        /*理论时长：2.0999635886641475小时
        总距离:1049.9817943320738km
        true  是否有风险*/

    }

    //-----------------2
    @Test
    public void test1(){
        HashMap<String, Map<String, Long>> historyhabit = new HashMap<>();//每周中的天
        HashMap<String, Long> houreofday  = new HashMap<>();//每天中的小时段
        houreofday.put("12",10L);//12点登陆了10次
        houreofday.put("13",1L);
        houreofday.put("18",3L);
        houreofday.put("17",1L);
        houreofday.put("21",4L);

        historyhabit.put("周五",houreofday);
        //当前时间
        /*习惯数据段;[12, 18, 21]	判断是否为习惯数据的阈值：2
        评估结果：false*/
        Boolean aBoolean = evalueLoginTimehobby(System.currentTimeMillis(), historyhabit, 10);
        System.out.println("评估结果："+aBoolean);
       // 评估结果：true

    }
    //------------------密码的评估模型
    //参3:安全阈值
    public Boolean evalueLoginPasswordSimilarity(String currentPassword,Set<String> historpassword,Double threhold){
        if (historpassword==null ||historpassword.size()==0){
            return false;//没风险
        }

        //建立词袋模型 （历史密码+当前密码 集合）
        Set<Character> bagofwordes= new HashSet<Character>();
        char[] chars = currentPassword.toCharArray();//当前密码转化为char数组
        //当前密码
        for (char aChar : chars) { //将字符存储到词袋子
            bagofwordes.add(aChar);
        }
        for (String s : historpassword) {  //获取历史的密码
            char[] chars1 = s.toCharArray();//历史的
            for (char historychar : chars1) {
                bagofwordes.add(historychar);//将
            }
        }

        //建立最终的词袋子模型
        List<Character> resultBageofword = bagofwordes
                .stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("历史密码以及当前密码词袋：");
        resultBageofword.stream().forEach(n-> System.out.print(n+"\t"));
        System.out.println();

        //--------建立向量，模型//当前密码
        HashMap<Character, Integer> charMap = new HashMap<>();//当前密码中字符出现次数的集合
        for (char currentChar : chars) {
            char c = currentChar;
            if (charMap.containsKey(c)){
                charMap.put(c,charMap.get(c)+1);//如果包含字符
            }else {
                charMap.put(c,1);//不包含是新添加设置为1；
            }
        }


        //---------当前密码的向量模型   和词袋子的大小
        Integer[] currentPasswordVector = new Integer[resultBageofword.size()];

        for (int i = 0; i < resultBageofword.size(); i++) {
            if (charMap.containsKey(resultBageofword.get(i))){
                currentPasswordVector[i]=charMap.get(resultBageofword.get(i));
            }else {
                currentPasswordVector[i]=0;
            }
        }
        System.out.println("当前密码向量：");
        Arrays.stream(currentPasswordVector).forEach(n-> System.out.print(n+"\t"));

        //----------------------历史登录密码向量----------
        //Integer 类型的数组
        List<Integer[]> historywordVectors = new ArrayList<>();
        for (String histpwd : historpassword) { //历史的set集合中的密码
            //每一个历史密码创建一个map集合
           Map<Character, Integer> history_chart_map = new HashMap<>();
            char[] histor_charyArray = histpwd.toCharArray();
            for (int i = 0; i < histor_charyArray.length; i++) {
                char c = histor_charyArray[i];
                if (history_chart_map.containsKey(c)){ //如果没有存在该字符
                    history_chart_map.put(c,history_chart_map.get(c)+1);
                }else{
                    history_chart_map.put(c,1);//添加新的字符
                }
            }
            //-----------向量的纬度等价于词袋子长度
            Integer[] historypasswordVector = new Integer[resultBageofword.size()];
            //对最终的词袋子遍历
            for (int i = 0; i < resultBageofword.size(); i++) {
                if(history_chart_map.containsKey(resultBageofword.get(i))){
                    //历史数据如果在词袋子中出现过 ,将该值放入历史密码向量中
                    historypasswordVector[i] = history_chart_map.get(resultBageofword.get(i));
                }else{
                    historypasswordVector[i]=0;//赋值为0，表示没有存在
                }
            }
          historywordVectors.add(historypasswordVector);

        }
        System.out.println("历史密码向量：");
        for (Integer[] historywordVector : historywordVectors) {
            for (Integer integer : historywordVector) {
                System.out.print(integer+"\n");
            }
        }

        //----------求向量夹角  获取历史存储的

        for (Integer[] historywordVector : historywordVectors) {
            Double fz = 0.0;//定义分子


            for (int i = 0; i < historywordVector.length; i++) {
                fz += historywordVector[i]*currentPasswordVector[i];
            }
            Double fm = Math.sqrt(Arrays.stream(historywordVector).map(n->n*n).reduce((v1,v2)->v1+v2).get())
                    *Math.sqrt(Arrays.stream(currentPasswordVector).map(n->n*n).reduce((v1,v2)->v1+v2).get());
            System.out.println("相似度："+fz/fm);
            if (fz/fm>threhold){ //相似度大于设定的相似度限制，没风险
                return false;
            }

        }

        return  true;//有风险
    }


    //--------------------------------输入特征的相似度判定 ,[100,200] 输入时间  //只保留用户最新的10个特征向量

    public  Boolean evaluInputFeature (Double[] currentFeatureVector ,List<Double[]> histtoryFeatureVectors ){

        if (histtoryFeatureVectors.size()<2||histtoryFeatureVectors==null){
            return false;//没有风险
        }

        //1----计算历史输入特征向量中心点      //历史数据中的元素 ，数组的长度
        Double[] sumvector = new Double[ histtoryFeatureVectors.get(0).length]; //[400D,600D,800D]
        for (Double[] histtoryFeatureVector : histtoryFeatureVectors) { //[100D,200D,300D],[200D,300D,400D],[300D,400D,500D]
            for (int i = 0; i < histtoryFeatureVector.length; i++) {
                if (sumvector[i]==null){
                    sumvector[i]=0.0;//给予一个初始值
                }
                sumvector[i] += histtoryFeatureVector[i];
            }

        }
        //中心点计算
        Double[] centerpoint = new Double[currentFeatureVector.length];
        for (int i = 0; i < centerpoint.length; i++) {
                                            //代表有几个独立的点
            centerpoint[i]= sumvector[i]/histtoryFeatureVectors.size();//例如 600/3
        }

        Arrays.stream(centerpoint).forEach(n-> System.out.print(n+","));
        //---------200.0,300.0,400.0

        //2--------计算历史输入特征 两两之间的中心点 ，鉴别偶然和常规事件
        List<Double> diatances = new ArrayList<>();
        for (int i = 0; i < histtoryFeatureVectors.size(); i++) {
            Double[] p1 = histtoryFeatureVectors.get(i);//拿到点坐标
            for (int j=i+1;j<histtoryFeatureVectors.size();j++){
                Double[] p2 = histtoryFeatureVectors.get(j);
                Double sum=0.0;
                for (int k=0;k<p1.length;k++){
                    sum +=Math.pow(p1[k]-p2[k],2);//求平方
                }
                //(100-200)²+(200-300)²+(300-400)² ≈173
                Double distance = Math.sqrt(sum);
                diatances.add(distance);
            }
        }
        System.out.println(diatances);


        //3-----样本升序排列，取1/3位置作为阈值
        diatances = diatances.stream().sorted().collect(Collectors.toList());
        // ---n*(n-1)/2  计算阈值
       Double thread = diatances.get(histtoryFeatureVectors.size()*(histtoryFeatureVectors.size()-1)/6);

        //4----计算当前输入点的中心距离
        Double sum =0.0;
        for (int i = 0; i < currentFeatureVector.length; i++) {

                  sum +=  Math.pow(currentFeatureVector[i]-centerpoint[i],2);
        }
        System.out.println("当前输入点距离"+Math.sqrt(sum)+"\t"+"安全阈值："+thread);


        //判断当前点与阈值做比对，看是否有风险
        return Math.sqrt(sum)>thread; //当前输入是否
    }

    //--------------------
    @Test
    public void test3(){

        ArrayList<Double[]> historyvector = new ArrayList<>();
        historyvector.add(new Double[]{100D,200D,300D});
        historyvector.add(new Double[]{200D,300D,400D});
        historyvector.add(new Double[]{300D,400D,500D});
        Boolean b =evaluInputFeature(new Double[]{200D,200D,300D},historyvector);
        System.out.println(b);
    }




    @Test
    public void test2(){
        HashSet<String> sets = new HashSet<>();
        sets.add("abc12345");
        sets.add("abc12");
       Boolean b = evalueLoginPasswordSimilarity("1a2b3c45",sets,0.8);
        System.out.println("是否有风险："+b);

        /*历史密码以及当前密码词袋：
        1	2	3	4	5	a	b	c
        当前密码向量：
        1	1	1	1	1	1	1	1	历史密码向量：*/

    }



    //------------------------二维相似度计算
    @Test
    public void t2(){
        Double point1[]={100.0,100.0};
        Double point2[]={200.0,200.0};


        Double fz =0.0;
        for (int i = 0;i<point1.length;i++){
            fz+= point1[i]*point2[i];
        }
        Double fm = Math.sqrt(Arrays.stream(point1).map(n -> n*n).reduce((v1,v2)->v1+v2 ).get())
                *Math.sqrt(Arrays.stream(point2).map(n -> n*n).reduce((v1,v2)->v1+v2 ).get());
        System.out.println("相似度："+fz/fm); //相似度：1.0
    }


    @Test
    public void t3(){
        HashSet<String> strings = new HashSet<>();
        strings.add("abc123");
        strings.add("abc12345");
        evalueLoginPasswordSimilarity("ud123",strings,1D);
    }


}
