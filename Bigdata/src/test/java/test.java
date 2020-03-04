import com.baizhi.entity.EvalueData;
import com.baizhi.entity.LoginSuccess;
import com.baizhi.util.LogParser;
import org.junit.Test;

import java.text.ParseException;

public class test {
    @Test
    public void test1() throws ParseException {
        //String log = "INFO 2019-11-25 14:11:00 app1 SUCCESS [zhangsan01] 6ebaf4ac780f40f486359f3ea6934620 \"123456bCA\" beijing \"116.4,39.5\" [1000,1300.0,1000.0] \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36\"";
        String log = "INFO 2019-11-25 14:11:00 app1 EVALUATE [zhangsan01] 6ebaf4ac780f40f486359f3ea6934620 \"123456bCA\" beijing \"116.4,39.5\" [1000,1300.0,1000.0] \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36\"";
        if (LogParser.isLoginSuccess(log)){
            LoginSuccess loginSuccess = LogParser.parserLoginSuccessData(log);
            System.out.println(loginSuccess);

        }else {
            EvalueData evalueData = LogParser.parserEvaluateData(log);
            System.out.println(evalueData);
            //EvalueData{currentTime=1574662260000, euipementId='app1', UserId='zhangsan01', loginSequenc='6ebaf4ac780f40f486359f3ea6934620', password='123456bCA', region='beijing', geoPoint=[116.4, 39.5], inputFeature=[1000.0, 1300.0, 1000.0], userAgent='Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36'}
        }

    }

}
