package com.lyoyang.utils;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ipaynow
 * Date: 18-8-10
 * Time: 下午12:48
 * To change this template use File | Settings | File Templates.
 */
public class HmacSha256Utils {

    private static final String EQUAL_DELIMITER = "=";

    private static final String SPLIT_DELIMITER = "&";

    /**
     * sha256_HMAC加密 * * @param message *消息 * @param secret *秘钥 * @return 加密后字符串
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

    /**
     * 将加密后的字节数组转换成字符串 * * @param b *字节数组 * @return 字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }


    public static boolean verifySign(Map<String, String> dataMap, String appKey) {
        StringBuilder builder = new StringBuilder();
        builder.append(RequestConstant.DATA).append(EQUAL_DELIMITER)
                .append(dataMap.get(RequestConstant.DATA)).append(SPLIT_DELIMITER)
                .append(RequestConstant.MESS).append(EQUAL_DELIMITER).append(dataMap.get(RequestConstant.MESS))
                .append(SPLIT_DELIMITER).append(RequestConstant.TIMESTAMP).append(EQUAL_DELIMITER).append(dataMap.get(RequestConstant.TIMESTAMP))
                .append(SPLIT_DELIMITER).append(RequestConstant.KEY).append(EQUAL_DELIMITER).append(appKey);
        String hmac = sha256_HMAC(builder.toString(), appKey);
        return hmac.equals(dataMap.get(RequestConstant.SIGN));
    }


    public static void main(String[] args) {
        String data = "data=0eUw2Nk2isX+IRlttM7eKomZCAfJlocPw2lG4uVrc0rOqzK85qW9tKshFrFmY8mrmD34UO6UBRpOaG4dKnENniZT3l0KSg/DnOG1L0xTwC+iW6M8mS5NJ9+HXTNduIeSGIpwhwWNXsOTedAK1JLKSWs9VyXSK1q2s0SUP6AeYoBuq6kMqR0m3Sa+o69UtVe7V9m6x61mzDueMFLQNXhTMdqL5n8ryGOUCNdBQ4jsHFrMZOudHY2+99ESv4trI/MpZL6KD2FKg1R1AkNy40/REVdtUnGSkJjj&mess=12313&timestamp=123457&key=78f9b4fad3481fbce1df0b30eee58577";
        String sign = HmacSha256Utils.sha256_HMAC(data, "78f9b4fad3481fbce1df0b30eee58577");
        System.out.println(sign);
    }

}
