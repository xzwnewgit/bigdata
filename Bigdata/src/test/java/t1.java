import com.baizhi.entity.LoginSuccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class t1 {
    final static Pattern EVAL_PATTERN = Pattern.compile("^INFO\\s(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\s(.*)\\sEVALUATE\\s\\[(.+)\\]\\s([a-z0-9]{32})\\s\"(.*)\"\\s([a-z]+)\\s\"([0-9\\.,]+)\"\\s\\[([0-9\\.,]+)\\]\\s\"(.*)\"");
    final static Pattern SUCCESS_PATTERN = Pattern.compile("^INFO\\s(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\s(.*)\\sSUCCESS\\s\\[(.+)\\]\\s([a-z0-9]{32})\\s\"(.*)\"\\s([a-z]+)\\s\"([0-9\\.,]+)\"\\s\\[([0-9\\.,]+)\\]\\s\"(.*)\"");

    public static void main(String[] args) throws ParseException {
        String loginSuccessData = "INFO 2019-11-25 14:11:00 app1 SUCCESS [zhangsan01] 6ebaf4ac780f40f486359f3ea6934620 \"123456bCA\" beijing \"116.4,39.5\" [1000,1300.0,1000.0] \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36\"";
        Matcher matcher = SUCCESS_PATTERN.matcher(loginSuccessData);
        //boolean r1 = matcher.matches();
        //System.out.println("是否匹配：" + r1);
        // 提取数据
        // long
        long currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(matcher.group(1)).getTime();
        String applicationId = matcher.group(2);
        String userId = matcher.group(3);
        String loginSequence = matcher.group(4);
        // 乱序的明文密码
        String orderlessPassword = matcher.group(5);
        String region = matcher.group(6);
        String[] arrAddress = matcher.group(7).split(",");
        Double[] geoPoint = {Double.parseDouble(arrAddress[0]), Double.parseDouble(arrAddress[1])};

        String[] arrInputFeature = matcher.group(8).split(",");
        Double[] inputFeature = new Double[arrInputFeature.length];
        for (int i = 0; i < arrInputFeature.length; i++) {
            inputFeature[i] = Double.parseDouble(arrInputFeature[i]);
        }

        String userAgent = matcher.group(9);
        LoginSuccess loginSuccess = new LoginSuccess(currentTime, applicationId, userId, loginSequence, orderlessPassword, region, geoPoint, inputFeature, userAgent);

        System.out.println(loginSuccess);
    }
}
