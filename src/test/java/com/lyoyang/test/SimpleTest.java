package com.lyoyang.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: Brian
 * @Date: 2020/6/12 10:00
 * @Description:
 */
@Slf4j
public class SimpleTest {

    @Test
    public void calc() throws InterruptedException {
//        BigDecimal bigDecimal = new BigDecimal("456565.65867876979");
//        System.out.println(bigDecimal.multiply(BigDecimal.valueOf(100L)).setScale(4, BigDecimal.ROUND_HALF_UP));
//        getNUm();
//        log.info("test msg [{}]", "12345");

        BlockingQueue blockingQueue = new LinkedBlockingQueue();
//        blockingQueue.offer("1");
//        blockingQueue.offer("2");
//        blockingQueue.offer("3");
//        blockingQueue.offer("4");
        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.take());

    }

;
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
