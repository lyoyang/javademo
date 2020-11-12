package com.lyoyang.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyoyang.utils.DateUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class SpiderTest {


    private static final String APPKEY = "tbw60Vi4O";

    private static final String APPSECRET = "A5tURz2JbjXxh7Su";



    @Test
    public void getTaobao() throws IOException {
//        String url = "https://rate.tmall.com/list_detail_rate.htmitemId=539137284584&spuId=701871908&sellerId=929347050&order=3&currentPage=1&append=0&content=1&tagId=&posi=&picture=&ua=098%23E1hvLpvWvRQvUvCkvvvvvjiPPFSptjlbPLsy6jYHPmP96jrWn2s9ljiEPFMyQjrURphvCvvvvvmCvpvW7D%2BnMq5w7Di4OzbNdphvHmQhsUE8o9v9BmeS8kH2mOcEmfwGiQhvCvvv9UUPvpvhvv2MMQhCvvOvUvvvphmivpvUvvmv%2BJZCZ94EvpvVmvvC9jxvKphv8vvvvvCvpvvvvvmmH6CvvHIvvUUdphvWvvvv9krvpv3Fvvmm86CvmVWEvpCWCh%2BMvvaw1WCl%2Bb8rwZHlYhzBRfpKofkXAf00Io3EAp0YyfUZEcqh1j7yHdUfbcc6D76fde%2BRfwLvaB46NZ59QnkQRqwiLO2vqU0QKLyCvvpvvvvv3QhvCvvhvvv%3D&isg=BBwcrmBIqyRNj10slC4flSrd7ToOPcHVm6szQvYdFofqQb3LHqQ2T4ezpam5SfgX&needFold=0&_ksTS=" + Integer.toString(LocalDateTime.now().getNano()) + "_664&callback=jsonp665";
        String url = "https://taoapi.ndxiu.com/service/get_detail_full.php?tid=%s&appkey=%s&sign=%s";
        url = String.format(url, "575690683769", APPKEY, getSign(APPKEY, APPSECRET, LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.FORMAT_DATE)), "575690683769"));
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        ResponseBody body = response.body();
        String res = new String(body.bytes(), "UTF-8");

        System.out.println(res);


    }



    @Test
    public void getComment() throws IOException {
        String url = "https://taoapi.ndxiu.com/service/get_comment.php?type=%s&tid=%s&appkey=%s&sign=%s&noncestr=%s&page=%s&sid=%s&ispic=1";
        String noceStr = getNoceStr(6);
        String tid = "575690683769";
        String sid = "352469034";
        url = String.format(url, "datalist", tid, APPKEY, getSign(APPKEY, APPSECRET, noceStr, tid), noceStr, 1, sid);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        ResponseBody body = response.body();
        String res = new String(body.bytes(), "UTF-8");
        System.out.println(res);
    }



    @Test
    public void getRandom() {
        Random random = new Random();
        System.out.println(random.nextInt(10));
    }


    private String getNoceStr(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        String str = "";
        for (int i = 0; i < length; i++) {
            str += chars.charAt(random.nextInt(chars.length()));
        }
        return str;
    }

    private String getSign(String appKey, String appSecret, String nonceStr, String tid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appKey).append(appSecret).append(nonceStr).append(tid);
        String md5Hex = DigestUtils.md5Hex(stringBuilder.toString());
        return md5Hex;
    }


}
