package com.lyoyang.test;


import java.math.BigDecimal;
import java.util.Random;

public class TestDemo {



    public static void main(String[] args) {
        Random random = new Random();
        BigDecimal fee = new BigDecimal("0.0067");
        BigDecimal spFee = new BigDecimal("0.0035");
        for (int i = 0 ; i < 100; i++) {
            int data = random.nextInt(9999999);
            BigDecimal amount = new BigDecimal(Integer.toString(data));
            BigDecimal feeValue = amount.multiply(fee).setScale(0, BigDecimal.ROUND_HALF_UP);
            BigDecimal spValue = amount.multiply(spFee).setScale(0, BigDecimal.ROUND_HALF_UP);
            BigDecimal bigDecimal = feeValue.subtract(spValue);
            BigDecimal distanceValue = amount.multiply(fee.subtract(spFee)).setScale(0, BigDecimal.ROUND_HALF_UP);
            if (bigDecimal.compareTo(distanceValue) != 0) {
                System.out.printf("amount=%s,no equal.distance=%s", amount, (bigDecimal.subtract(distanceValue)));
                System.out.println();
            }
        }
    }


}
