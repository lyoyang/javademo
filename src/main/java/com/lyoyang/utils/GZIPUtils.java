package com.lyoyang.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author: yangbing
 * @Date: 2020/4/15 18:44
 * @Description:
 */
public class GZIPUtils {

    public static String compress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        // 将字节写入此输出流
        //因为后台默认字符集有可能是GBK字符集，所以此处需指定一个字符集
        gzip.write(str.getBytes("utf-8"));
        gzip.close();
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("iso-8859-1");
    }

    /**
     * 字符串的解压
     * @param str 对字符串解压
     * @return 返回解压缩后的字符串
     * @throws IOException
     */
    public static String unCompress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        // 使用默认缓冲区大小创建新的输入流
        GZIPInputStream gzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n = 0;

        // 将未压缩数据读入字节数组
        while ((n = gzip.read(buffer)) >= 0){
            out.write(buffer, 0, n);
        }
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("utf-8");
    }


    public static void main(String[] args) throws IOException {
        String str = "a gzip test";
        String compress = GZIPUtils.compress(str);
        System.out.println(compress);
        String unCompress = GZIPUtils.unCompress(compress);
        System.out.println(unCompress);
    }

}
