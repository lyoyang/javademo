package com.lyoyang.test;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.sun.org.apache.xpath.internal.operations.Bool;
import okhttp3.Call;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.*;

public class SimpleTest {


    private final ExecutorService ss = Executors.newFixedThreadPool(3);


    @Test
    public void test() {
//        String str = "https://detail.tmall.com/item.htm?id=622072218724&ali_trackid=2:mm_719230008_1083850282_109754100405:1603847284_198_141703264&union_lens=lensId:PUB@1602314091@10c778ed-70a0-40d0-a193-030f19305315_622072218724@0238NCQoD9EHUo1GPqO8D7Ry;recoveryid:1603847284_198_141703264&pvid=null&scm=null&bxsign=tbkubvkt8Zxmufsa9B/ExnSfY4Jv3IR22la07MMkT32neuTJLVchfGY7i6VEQGQXL85Ocp8yLMSQDbq2Tfx3uo2yryxy1jJ4m089nuUAqzEr8c=&skuId=4397338389281";
        String str = "https://item.jd.com/5657657567567.html?extension_id=eyJhZCI6IjE3NTUiLCJjaCI6IjIiLCJza3UiOiIxMDAwMDg4OTM4MTMiLCJ0cyI6IjE2MDM4NDkxMzQiLCJ1bmlxaWQiOiJ7XCJjbGlja19pZFwiOlwiODE1YjQyY2EtN2Y5YS00ZTQ0LWFiMTgtNmZmN2M2OGI3MmI1XCIsXCJtYXRlcmlhbF9pZFwiOlwiMjgwODgwNDY1NlwiLFwicG9zX2lkXCI6XCIxNzU1XCIsXCJzaWRcIjpcIjBmYjgyNjI0LTJiNjktNGY1OC1hZWZjLTA3ZTAwNDEyYzBlZVwifSJ9&jd_pop=815b42ca-7f9a-4e44-ab18-6ff7c68b72b5&abt=0";
//        boolean contains = str.contains("id=");
        boolean matches = str.matches(".*\\d+(\\.html).*");
        System.out.println(matches);
        String s = StringUtils.substringBetween(str, "item.jd.com/", ".html");
        System.out.println(s);
//        System.out.println(contains);
//        List<NameValuePair> parse = URLEncodedUtils.parse(str, StandardCharsets.UTF_8);
//        System.out.println(parse);
//        String s = StringUtils.substringBetween(str, "id=", "&");
//        System.out.println(s);

        Future<Boolean> submit = ss.submit(new TestRunner());
        Boolean aBoolean = null;
        try {
            aBoolean = submit.get();
            if (aBoolean) {
                System.out.println("test sucess");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("hello___________");


    }

    class TestRunner implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            TimeUnit.SECONDS.sleep(60);
            return true;
        }
    }


}