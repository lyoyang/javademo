package com.lyoyang.enums;

public enum ChannelTypeMappingEnum {
    WECHAT("80", 0, "WeChat"),
    ALIPAY("90", 1, "AliPay"),
    ALIPAYWECHAT("80|90", 2, "AlipayWeChat");


    private String typeCode;
    private Integer accChannelId;
    private String typeName;

    private ChannelTypeMappingEnum(String typeCode, Integer accChannelId, String typeName) {
        this.typeCode = typeCode;
        this.accChannelId = accChannelId;
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public Integer getAccChannelId() {
        return accChannelId;
    }

    public static ChannelTypeMappingEnum buildByTypeCode(String typeCode) {
        ChannelTypeMappingEnum[] allNowpayOrderType = values();
        for (ChannelTypeMappingEnum orderType : allNowpayOrderType) {
            String nowTypeCode = orderType.getTypeCode();
            if (nowTypeCode.equals(typeCode)) return orderType;
        }
        return null;
    }



}
