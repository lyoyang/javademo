package com.lyoyang.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FeeConfig implements Serializable {
    private Long id;

    private String channelId;

    private String deviceId;

    private String mchId;

    private String transType;

    private String status;

    private String feeMode;

    private BigDecimal ratioValue;

    private BigDecimal feeValue;

    private BigDecimal rateMaxValue;

    private BigDecimal rateMinValue;

    private String merRefundFee;

    private String monthType;

    private String isDafault;

    private Date beginDate;

    private BigDecimal ratioValueChannel;

    private BigDecimal feeValueChannel;

    private BigDecimal rateMaxValueChannel;

    private BigDecimal rateMinValueChannel;

    private String feeModeChannel;

    private String cardType;

    private String cardBrand;

    private String dccMode;

    private String remark;

    private String subDeviceId;

    private String belongBank;

    private String cardholderProperty;

    private String deviceType;

    @Override
    public String toString() {
        return "FeeConfig{" +
                "channelId='" + channelId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", mchId='" + mchId + '\'' +
                '}';
    }
}