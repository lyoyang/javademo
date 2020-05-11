package com.lyoyang.test;

import com.alibaba.fastjson.JSONObject;
import com.lyoyang.utils.Des3Util;
import com.lyoyang.utils.HmacSha256Utils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yangbing
 * @Date: 2020/4/7 15:33
 * @Description:
 */
public class JiaXinTangRequest {

    private static final String mess = "01234567";
    private static final String timestamp = "20200407133443";
    private static final String signType = "sha256";
    private static final String key = "W7Jj72ZT2j4CdAY3RfXfAr8h";
    private static final String appKey = "8zEkVRqfeqPJip0mDepoHMKN1q60tBKp";




    /**
     * 银行卡下单
     * @throws Exception
     */
    @Test
    public void realtimeOrder() throws Exception {
        String url = "http://localhost:8089/jiaxin-pay/api/payment/v1/order-realtime";
//        String url = "http://192.168.99.61:8089/api/payment/v1/order-realtime";
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", "87675654768797547656757");
        params.put("dealer_id", "000000100538113");
        params.put("broker_id", "1");
        params.put("real_name", "乔孟");
        params.put("card_no", "6214680048909301");
        params.put("phone_no", "18511304822");
        params.put("id_card", "410881199602215017");
        params.put("pay", "100");
        params.put("anchor_id", "1");
        params.put("notes", "{\"userWorkload\":\"1\",\"userWorkloadLevel\":[{\"proj\":\"项目A:直播时长\",\"std\":\"5元/小时\",\"val\":\"20\"},{\"proj\":\"项目B:礼物数\",\"std\":\"5元/小时\",\"val\":\"20\"},{\"proj\":\"项目B:直播人气\",\"std\":\"5元/小时\",\"val\":\"20\"},{\"proj\":\"项目f\",\"std\":\"3元/小时\",\"val\":\"20\"}]}\n");
        params.put("pay_remark", "");
        params.put("notifyUrl", "http://192.168.99.61:8089/api/payment/v1/orderNotify");
        String data = getEncryptData(params, true);
        HashMap<String, String> header = new HashMap<>();
        header.put("dealer-id", "000000100538113");
        header.put("request-id", "678678678678");
//        String res = HttpsClientUtil.sendPost(url, data, header);
//        System.out.println(res);
    }


    /**
     * 支付宝下单
     * @throws Exception
     */
    @Test
    public void realtimeAli() throws Exception {
//        String url = "http://localhost:8089/api/payment/v1/order-alipay";
        String url = "http://192.168.99.61:8089/api/payment/v1/order-alipay";
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", "87675655465787680887435632");
        params.put("dealer_id", "000000100538113");
        params.put("broker_id", "123");
        params.put("real_name", "杨兵");
        params.put("card_no", "15735104203");
        params.put("phone_no", "15735104203");
        params.put("id_card", "141127199308280095");
        params.put("pay", "100");
        params.put("anchor_id", "1");
        params.put("notes", "{\"userWorkload\":\"1\",\"userWorkloadLevel\":[{\"proj\":\"项目A:直播时长\",\"std\":\"5元/小时\",\"val\":\"20\"},{\"proj\":\"项目B:礼物数\",\"std\":\"5元/小时\",\"val\":\"20\"},{\"proj\":\"项目B:直播人气\",\"std\":\"5元/小时\",\"val\":\"20\"},{\"proj\":\"项目f\",\"std\":\"3元/小时\",\"val\":\"20\"}]}\n");
        params.put("pay_remark", "");
        params.put("notifyUrl", "http://192.168.99.61:8089/api/payment/v1/orderNotify");
        String data = getEncryptData(params, true);
        HashMap<String, String> header = new HashMap<>();
        header.put("dealer-id", "000000100538113");
        header.put("request-id", "678678678678");
//        String res = HttpsClientUtil.sendPost(url, data, header);
//        System.out.println(res);
    }


    /**
     * 微信下单
     * @throws Exception
     */
    @Test
    public void realtimeWechat() throws Exception {
        String url = "http://localhost:8089/api/payment/v1/order-wxpay";
//        String url = "http://192.168.99.61:8089/api/payment/v1/order-wxpay";
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", "87675654768743565677098");
        params.put("dealer_id", "000000100538113");
        params.put("broker_id", "123");
        params.put("real_name", "杨兵");
        params.put("id_card", "141127199308280095");
        params.put("openid", "5675676565");
        params.put("pay", "100");
        params.put("open_id", "454645645");
        params.put("notes", "{\"userWorkload\":\"1\",\"userWorkloadLevel\":[{\"proj\":\"项目A:直播时长\",\"std\":\"5元/小时\",\"val\":\"20\"},{\"proj\":\"项目B:礼物数\",\"std\":\"5元/小时\",\"val\":\"20\"},{\"proj\":\"项目B:直播人气\",\"std\":\"5元/小时\",\"val\":\"20\"},{\"proj\":\"项目f\",\"std\":\"3元/小时\",\"val\":\"20\"}]}\n");
        params.put("pay_remark", "");
        params.put("notifyUrl", "http://192.168.99.61:8089/api/payment/v1/orderNotify");
        String data = getEncryptData(params, true);
        HashMap<String, String> header = new HashMap<>();
        header.put("dealer-id", "000000100538113");
        header.put("request-id", "678678678678");
//        String res = HttpsClientUtil.sendPost(url, data, header);
//        System.out.println(res);
    }




    private HashMap<String, String> buildPostParam(String data, String sign) {
        HashMap<String, String> map = new HashMap<>();
        map.put("data", data);
        map.put("mess", mess);
        map.put("timestamp", timestamp);
        map.put("key", appKey);
        map.put("sign", sign);
        return map;
    }


    /**
     * 查询订单
     */
    @Test
    public void queryOrder() throws Exception {
//        String url = "http://localhost:8089/api/payment/v1/query-realtime-order";
//        String url = "http://192.168.99.61:8089/api/payment/v1/query-realtime-order";
        String url = "https://tang-api.ipaynow.cn/jiaxin-pay/api/payment/v1/query-realtime-order";
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", "5678913c41a45-84e7-42f5-a3f3-fe1975b7bf93");
        params.put("channel", "支付宝");
//        params.put("data_type", "encryption");
        String data = getEncryptData(params, true);
        url = url + "?" + data;
        Map<String, String> header = getHeader("000000001572907", "7657867868");
//        String res = HttpsClientUtil.doGet(url, StandardCharsets.UTF_8.name(), header);
//        System.out.println(res);
    }

    /**
     * 查询账户余额
     */
    @Test
    public void queryAccountBalance() throws Exception {
        String url = "http://localhost:8089/api/payment/v1/query-accounts";
//        String url = "http://192.168.99.61:8089/api/payment/v1/query-accounts";
        Map<String, Object> params = new HashMap<>();
        params.put("dealer_id", "000000100538113");
        String data = getEncryptData(params, true);
        url = url + "?" + data;
        Map<String, String> header = getHeader("000000100538113", "7657867868");
//        String res = HttpsClientUtil.doGet(url, StandardCharsets.UTF_8.name(), header);
//        System.out.println(res);
    }


    /**
     * 取消待打款订单
     */
    @Test
    public void cancelOrder() throws Exception {
        String url = "http://localhost:8089/api/payment/v1/order/fail";
//        String url = "http://192.168.99.61:8089/api/payment/v1/order/fail";
        Map<String, Object> params = new HashMap<>();
        params.put("dealer_id", "000000100538113");
        params.put("order_id", "5345363636311");
        params.put("ref", "1248497464885915650");
        params.put("channel", "银行卡");
        String data = getEncryptData(params, true);
        Map<String, String> header = getHeader("000000100538113", "7657867868");
//        String res = HttpsClientUtil.sendPost(url, data , header);
//        System.out.println(res);
    }


    /**
     *查询电子回单
     */
    @Test
    public void queryReceiptFile() throws Exception {
//        String url = "http://localhost:8089/jiaxin-pay/api/payment/v1/receipt/file";
//        String url = "http://192.168.99.61:8089/jiaxin-pay/api/payment/v1/receipt/file";
        String url = "https://tang-api.ipaynow.cn/jiaxin-pay/api/payment/v1/receipt/file";
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", "5678916694871-1825-4da2-b085-10915fc8afb4");
//        params.put("ref", "1248513674461589506");
        String data = getEncryptData(params, true);
        url = url + "?" + data;
        Map<String, String> header = getHeader("000000001572907", "7657867868");
//        String res = HttpsClientUtil.doGet(url, StandardCharsets.UTF_8.name(), header);
//        System.out.println(res);
    }


    /**
     *查看用户打款金额是否超出全网月限额
     */
    @Test
    public void riskCheckAmount() throws Exception {
        String url = "http://localhost:8089/api/payment/v1/risk-check/amount";
//        String url = "http://192.168.99.61:8089/api/payment/v1/risk-check/amount";
        Map<String, Object> params = new HashMap<>();
        params.put("id_card", "410881199602215017");
        params.put("real_name", "乔孟");
        params.put("broker_id", "yiyun73");
        params.put("amount", "2.00");
        String data = getEncryptData(params, true);
        Map<String, String> header = getHeader("000000100538113", "7657867868");
//        String res = HttpsClientUtil.sendPost(url, data , header);
//        System.out.println(res);
    }


    /**
     * 日订单文件
     */
    @Test
    public void dayOrderUrl() throws Exception {
//        String url = "http://localhost:8089/api/dataservice/v1/order/downloadurl";
//        String url = "http://192.168.99.61:8089/api/dataservice/v1/order/downloadurl";
        String url = "https://tang-api.ipaynow.cn/jiaxin-pay/api/dataservice/v1/order/downloadurl";
        Map<String, Object> params = new HashMap<>();
        params.put("order_date", "2020-04-21");
        String data = getEncryptData(params, true);
        url = url + "?" + data;
        Map<String, String> header = getHeader("000000001572907", "7657867868");
//        String res = HttpsClientUtil.doGet(url, StandardCharsets.UTF_8.name(), header);
//        System.out.println(res);
    }


    /**
     * 日流水
     */
    @Test
    public void dayTransUrl() throws Exception {
//        String url = "http://localhost:8089/api/dataservice/v1/order/downloadurl";
//        String url = "http://192.168.99.61:8089/api/dataservice/v1/order/downloadurl";
        String url = "https://tang-api.ipaynow.cn/jiaxin-pay/api/dataservice/v2/bill/downloadurl";
        Map<String, Object> params = new HashMap<>();
        params.put("bill_date", "2020-04-21");
        String data = getEncryptData(params, true);
        url = url + "?" + data;
        Map<String, String> header = getHeader("000000001572907", "7657867868");
//        String res = HttpsClientUtil.doGet(url, StandardCharsets.UTF_8.name(), header);
//        System.out.println(res);
    }



    @Test
    public void testCreateFile() throws IOException {
        String filePath = "E:\\test\\a\\b\\a.txt";
//        FileUtils.write(new File(filePath), "hello", StandardCharsets.UTF_8);
//        FileUtils.deleteQuietly(new File(filePath));
//        FileUtils.forceDelete(new File(filePath));

        if (Files.exists(Paths.get(filePath))) {
            FileUtils.deleteQuietly(new File(filePath));
        }

    }




    /**
     * 日流水
     */
    @Test
    public void dayTrans() {

    }







    private Map<String, String> getHeader(String dealerId, String requestId) {
        HashMap<String, String> header = new HashMap<>();
        header.put("dealer-id", dealerId);
        header.put("request-id", requestId);
        return header;
    }



    private String getEncryptData(Map<String, Object> param, boolean encode) throws Exception {
        String data = Des3Util.encrypt3Des(JSONObject.toJSONString(param).getBytes(), key.getBytes());
        StringBuilder builder = new StringBuilder();
        builder.append("data=").append(data).append("&mess=").append(mess)
                .append("&timestamp=").append(timestamp).append("&key=").append(appKey);
        String sign = HmacSha256Utils.sha256_HMAC(builder.toString(), appKey);
        if (encode) {
            data = URLEncoder.encode(data, "UTF-8");
        }
        StringBuilder reqBuilder = new StringBuilder();
        reqBuilder.append("data=").append(data).append("&mess=").append(mess)
                .append("&timestamp=").append(timestamp).append("&sign=").append(sign)
                .append("&sign_type=").append(signType);
        return reqBuilder.toString();
    }













}
