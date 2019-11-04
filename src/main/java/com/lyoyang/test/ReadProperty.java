package com.lyoyang.test;

import com.alibaba.excel.annotation.ExcelProperty;

import java.math.BigDecimal;

/**
 * @author: yangbing
 * @Date: 2019/9/6 17:59
 * @Description:
 */
public class ReadProperty {

    @ExcelProperty()
    private String mchId;

    private String mchName;

    private BigDecimal amount;

    private Integer transNum;

    private BigDecimal refundAmount;

    private Integer awareNum;

    private  BigDecimal transD;

    private Integer actualNum;

    private BigDecimal percent;


}
