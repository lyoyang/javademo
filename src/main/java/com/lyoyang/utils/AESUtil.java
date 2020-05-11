
package com.lyoyang.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES加密和解密工具类
 *
 * @author zhancy
 */
public class AESUtil {

    // AES密码器
    private static Cipher cipher;

    // 字符串编码
    private static final String KEY_CHARSET = "UTF-8";

    // 算法方式
    private static final String KEY_ALGORITHM = "AES";

    // 算法/模式/填充
    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";

    // 私钥大小128/192/256(bits)位 即：16/24/32bytes，暂时使用128，如果扩大需要更换java/jre里面的jar包
    private static final Integer PRIVATE_KEY_SIZE_BIT = 128;
    private static final Integer PRIVATE_KEY_SIZE_BYTE = 16;


    public static void main(String[] args) {

        String secretKey = "c9997ab525a76a09";
        String keyForAgent = "8228b4a15cc32bd6";
        String keyForCross = "2acfc577a341e729";
        String text = "{\"accDate\":\"2018-12-19\",\"amount\":\"50000\",\"bankCost\":\"0\",\"bankProfit\":\"0\",\"bankRebateIpaynow\":\"0\",\"fee\":\"2500\",\"feeChannel\":\"0\",\"feeRatio\":\"0.000000\",\"ipaynowAmount\":\"2395\",\"ipaynowRebateMch\":\"0\",\"mchId\":\"000000100518976\",\"settleAmount\":\"47500\",\"settleAmountChannel\":\"0\",\"settleDate\":\"2018-12-19\",\"settleFlag\":\"1\",\"spFee\":\"105\",\"transDate\":\"2018-12-18 10:18:08\",\"transId\":\"2018121711244134583266\"}";
//        String miwen2 = encryptForDataCenter(secretKey, text);
//        System.out.println("test len:" + String.valueOf(text.length()));
//        String mingwen = decrypt(secretKey, miwen);
//        int a = 2;
//        String miwen2 = encryptForDataCenter(secretKey, text);
//        System.out.println("密文为：" + miwen);
        String miwen = "Nm9j5XNpD9mnEeIfhLLPgObW3NeQQ5Ws2x9+Zf3FBIDw4/Ac/F66bLNRQ03QFu42X87HN6eyojkpogsWUEqUFZhnOHdc9CI1PYBLf1FeWSMqXG7IdwJHRSSqScH1EYRAuezQTn2Pylzn4OzE8En8DYr+MTcrlIa58k0gS9XdotJfIeWprVk0UvnG3Ic1BSS4JoH9pwRLyOUX2lddCTWnK00lGmnuUAmkKCVfSEzk3snBuzFc6bdjTIhh5+CX/eFMavZhIIdyMgqrJbXe7TxljBrocC+STCYMRhcPguntrXHSeEQ01X6wdspsOaYrNRL6+WYd9UvrYbdL3UIwfXTbMb9sreb7cKE0tg68QJbC0gXLS/YMBT5IKIP9cRQHRHZVw4bjUqxxC01Lz0s0SC729z2JOo0OUhqJj453L3ux7/3FXrPt1OV912hq98Td/ouYaYFfo/rgRAsOAH6csfkKq8OmBvQFqARXu2Skr2AaWbRkxQxWAEJGWDstsd1GVCHcQEI/3/koJBlChEwzrt52Yg==";
        String mmm = decrypt(secretKey, miwen);
        System.out.println(mmm.length());
        int a = 3;
//        System.out.println(mmm.length());
//        String mingwen = decrypt(secretKey, "Nm9j5XNpD9mnEeIfhLLPgMbUuiACi4R6NnDIjIw+LI0+sBPFEfAixFnkcQMsZapkYR8i2Nu2Fp6fPftAaiGnlEpzAOAD72czplp2eoub0FGhBVLzIX7RzjcBnhIhBGfTA5xCaTOKbQv6QrFTod0QL2ErkBy6NVrhjqxP8XsWt+L9F+qZkzttibZvOg6VgO4srWDRpFgPtyGNAQ8lVjtYDeTvbkKOX0UKKFX07xNA3SmJdgi7VKAbJg1p4qW5zNyh7Rn4g8oLCs935YTLnWvPoyT4+KkKRINVcFDIk27oWDXHyTFYO3cdDUMYdhvWxNovrqaWAH/teltEk5A8yikt1P/LNw42jzErdGa3XftlaEn87g4wDLXQgGo2fA7saNCIBwLtyqeUipR8zVKqdx4FcCQ0c+9TlDZ4mRz8UHMzAy1P/zhZWPy8nHo8BQjRe5IsuCU37tLhqeEpiHnwOnFqT6zD2bdUGiYwc0NB2ot5E1MWDW0D5zSylQShq4KIst7SM+7r2fGvBwBrM7lPyf301JXxky7E9s7YaVwQ/N5p1I8=");
//        System.out.println("明文为：" + mingwen);
    }

    /**
     * 加密
     *
     * @param secretKey 密钥：加密的规则 16位
     * @param plainText 明文：要加密的内容
     * @return cipherText
     * 密文：加密后的内容，如有异常返回空串：""
     */
    public static String encrypt(String secretKey, String plainText) {
        if (secretKey.length() != PRIVATE_KEY_SIZE_BYTE) {
            throw new RuntimeException("AESUtil:Invalid AES secretKey length (must be 16 bytes)");
        }

        // 密文字符串
        String cipherText = "";
        try {
            // 加密模式初始化参数
            initParam(secretKey, Cipher.ENCRYPT_MODE);
            // 获取加密内容的字节数组
            byte[] bytePlainText = plainText.getBytes(KEY_CHARSET);
            // 执行加密
            byte[] byteCipherText = cipher.doFinal(bytePlainText);
            cipherText = Base64.encodeBase64String(byteCipherText);
        } catch (Exception e) {
            throw new RuntimeException("AESUtil:encrypt fail!", e);
        }
        return cipherText;
    }

    /**
     * 解密
     *
     * @param secretKey  密钥：加密的规则 16位
     * @param cipherText 密文：加密后的内容，即需要解密的内容
     * @return plainText
     * 明文：解密后的内容即加密前的内容，如有异常返回空串：""
     */
    public static String decrypt(String secretKey, String cipherText) {
        if (secretKey.length() != PRIVATE_KEY_SIZE_BYTE) {
            throw new RuntimeException("AESUtil:Invalid AES secretKey length (must be 16 bytes)");
        }
        // 明文字符串
        String plainText = "";
        try {
            initParam(secretKey, Cipher.DECRYPT_MODE);
            // 将加密并编码后的内容解码成字节数组
            byte[] byteCipherText = Base64.decodeBase64(cipherText);
            // 解密
            byte[] bytePlainText = cipher.doFinal(byteCipherText);
            plainText = new String(bytePlainText, KEY_CHARSET);
        } catch (Exception e){
            System.out.println("AESUtil:decrypt fail! secretKey: {}, cipherText: {},exception: {}");
            throw new RuntimeException("AESUtil:decrypt fail!", e);
        }
        return plainText;
    }

    /**
     * 初始化参数
     *
     * @param secretKey 密钥：加密的规则 16位
     * @param mode      加密模式：加密or解密
     */
    public static void initParam(String secretKey, int mode) {
        try {
            // 防止Linux下生成随机key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(secretKey.getBytes());
            // 获取key生成器
            KeyGenerator keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
            keygen.init(PRIVATE_KEY_SIZE_BIT, secureRandom);

            // 获得原始对称密钥的字节数组
            byte[] raw = secretKey.getBytes();

            // 根据字节数组生成AES内部密钥
            SecretKeySpec key = new SecretKeySpec(raw, KEY_ALGORITHM);
            // 根据指定算法"AES/CBC/PKCS5Padding"实例化密码器
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            IvParameterSpec iv = new IvParameterSpec(secretKey.getBytes());
            cipher.init(mode, key, iv);
        } catch (Exception e) {
            throw new RuntimeException("AESUtil:initParam fail!", e);
        }
    }


    /**
     * 加密
     *
     * @param secretKey 密钥：加密的规则 16位
     * @param plainText 明文：要加密的内容
     * @return cipherText
     * 密文：加密后的内容，如有异常返回空串：""
     */
    public static String encryptForDataCenter(String secretKey, String plainText) {
        if (secretKey.length() != PRIVATE_KEY_SIZE_BYTE) {
            throw new RuntimeException("AESUtil:Invalid AES secretKey length (must be 16 bytes)");
        }
        // 密文字符串
        String cipherText = "";
        try {
            // 加密模式初始化参数
            initParam2(secretKey, Cipher.ENCRYPT_MODE);
            // 获取加密内容的字节数组
            byte[] bytePlainText = plainText.getBytes(KEY_CHARSET);
            // 执行加密
            byte[] byteCipherText = cipher.doFinal(bytePlainText);
            cipherText = Base64.encodeBase64String(byteCipherText);
        } catch (Exception e) {
            throw new RuntimeException("AESUtil:encrypt fail!", e);
        }
        return cipherText;
    }


    /**
     * 初始化参数
     *
     * @param secretKey 密钥：加密的规则 16位
     * @param mode      加密模式：加密or解密
     */
    public static void initParam2(String secretKey, int mode) {
        try {
            // 防止Linux下生成随机key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(secretKey.getBytes());
            // 获取key生成器
            KeyGenerator keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
            keygen.init(PRIVATE_KEY_SIZE_BIT, secureRandom);

            // 获得原始对称密钥的字节数组
            byte[] raw = secretKey.getBytes();

            // 根据字节数组生成AES内部密钥
            SecretKeySpec key = new SecretKeySpec(raw, KEY_ALGORITHM);
            // 根据指定算法"AES/CBC/PKCS5Padding"实例化密码器
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            IvParameterSpec iv = new IvParameterSpec(secretKey.getBytes());
            cipher.init(mode, key, iv);
        } catch (Exception e) {
            throw new RuntimeException("AESUtil:initParam fail!", e);
        }
    }



}


