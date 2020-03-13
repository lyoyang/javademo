package com.lyoyang.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IpaynowTest {

    @Test
    public void testKs() {

        BigDecimal b1 = calcIncome(Arrays.asList("6"), new BigDecimal("0.008"), new BigDecimal("0.0026"), new BigDecimal("0.002"));
        BigDecimal b2 = calcIncome(Arrays.asList("68000",
                "13600",
                "100",
                "12900"), new BigDecimal("0.006"), new BigDecimal("0.0026"), new BigDecimal("0.002"));
        System.out.println(b1.add(b2).toPlainString());
        System.out.println(b1.toPlainString());

    }



    private BigDecimal calcIncome(List<String> amounts, BigDecimal feeRatio, BigDecimal ipaynowCostRatio, BigDecimal ksCostRatio) {
        List<BigDecimal> incomeList = new ArrayList<>();
        // 交易金额
        for (String amountStr : amounts) {
            BigDecimal amount = new BigDecimal(amountStr);
            //手续费(交易金额*手续费率)
            BigDecimal fee = amount.multiply(feeRatio).setScale(0, BigDecimal.ROUND_HALF_UP);
            //商户结算金额(交易金额-手续费)
            BigDecimal settleAmount = amount.subtract(fee);
            //客商实收金额(交易金额-(交易金额*客商成本))
            BigDecimal ksRealAmount = amount.subtract((amount.multiply(ksCostRatio).setScale(0, BigDecimal.ROUND_HALF_UP)));
            //客商结算金额(客商实收金额-商户结算金额)
            BigDecimal ksSettleAmount = ksRealAmount.subtract(settleAmount);
            // 客商的纯收益 交易金额*（我司成本-客商成本）
            BigDecimal ksIncomeAmount = amount.multiply((ipaynowCostRatio.subtract(ksCostRatio))).setScale(0, BigDecimal.ROUND_HALF_UP);
            // 我是收益 客商结算金额-客商的纯收益

//        BigDecimal ipaynowIncomeAmount = ksSettleAmount.subtract(ksIncomeAmount);
            BigDecimal ipaynowIncomeAmount = amount.multiply((feeRatio.subtract(ipaynowCostRatio)))
                    .setScale(0, BigDecimal.ROUND_HALF_UP);
            if (ipaynowIncomeAmount.compareTo((ksSettleAmount.subtract(ksIncomeAmount))) <= 0) {
                if ((ipaynowIncomeAmount.add(ksIncomeAmount)).compareTo(ksSettleAmount) != 0) {
                    ipaynowIncomeAmount = ksSettleAmount.subtract(ksIncomeAmount);
                }
            } else {
                ipaynowIncomeAmount = ksSettleAmount.subtract(ksIncomeAmount);
            }
            incomeList.add(ipaynowIncomeAmount);
        }
        BigDecimal totalIncome = BigDecimal.ZERO;
        for (BigDecimal e : incomeList) {
            totalIncome = totalIncome.add(e);
        }
        return totalIncome;
    }




}
