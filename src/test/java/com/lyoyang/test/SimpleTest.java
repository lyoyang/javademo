package com.lyoyang.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author: Brian
 * @Date: 2020/6/12 10:00
 * @Description:
 */
@Slf4j
public class SimpleTest {

    @Test
    public void calc() throws IOException {
//        BigDecimal bigDecimal = new BigDecimal("456565.65867876979");
//        System.out.println(bigDecimal.multiply(BigDecimal.valueOf(100L)).setScale(4, BigDecimal.ROUND_HALF_UP));
//        getNUm();
//        log.info("test msg [{}]", "12345");

//        String str = "56464564";
//        int h = str.hashCode();
//        System.out.println(h ^ (h >>> 16));
        String str = "hello";

//        Files.write(str, new File("D://pacypay/2020-07-03/temp.txt"), Charset.forName("GBK"));

//        Files.write(Paths.get("D://pacypay/2020-07-03/temp.txt"), str.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);

        FileUtils.write(new File("D://pacypay/2020-07-03/temp.txt"), str, Charset.forName("GBK"));
//        RandomAccessFile randomAccessFile = new RandomAccessFile("D://pacypay/2020-07-03/temp.txt", "rwd");
//        FileChannel channel = randomAccessFile.getChannel();
//        ByteBuffer allocate = ByteBuffer.allocate(str.getBytes().length);
//        allocate.put(str.getBytes());
//        channel.write(allocate);
//        allocate.clear();
//        randomAccessFile.close();


    }


    @Test
    public void test() {
        BigDecimal ratio = new BigDecimal("0.0038");
        for (int i=1; i<10000000; i++) {
            BigDecimal amount_05 = new BigDecimal(""+i);
            BigDecimal fee_05 = amount_05.multiply(ratio).setScale(0, BigDecimal.ROUND_HALF_UP);

            BigDecimal amount_03_1 = new BigDecimal(""+ (i/2) );
            BigDecimal fee_03_1 = amount_03_1.multiply(fee_05).divide(amount_05, 0, BigDecimal.ROUND_HALF_UP);

            BigDecimal amount_03_2 = amount_05.subtract(amount_03_1);
            BigDecimal fee_03_2 = amount_03_2.multiply(fee_05).divide(amount_05, 0, BigDecimal.ROUND_HALF_UP);
            if (fee_05.compareTo((fee_03_1.add(fee_03_2))) != 0) {
                System.out.println("amount_05=" + amount_05.toPlainString() + " fee_05=" + fee_05.toPlainString()+ " amount_03_1=" + amount_03_1.toPlainString()+ " fee_03_1=" + fee_03_1.toPlainString()+ " amount_03_2=" + amount_03_2.toPlainString()+ " fee_03_2=" + fee_03_2.toPlainString());
            }
        }
    }


}
