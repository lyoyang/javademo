package com.lyoyang.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CalculationSms extends CalculationSmsKey {
    private String mchOrderNo;

    private String userId;

    private String appId;

    private String mchId;

    private String channelId;

    private String requestId;

    private String channelTransId;

    private Date transDate;

    private BigDecimal fee;

    private BigDecimal spFee;

    private BigDecimal ipaynowAmount;

    private String settleFlag;

    private String channelTransDate;

    private String accDate;

    private String settleDate;

    private String isTransfered;

    private String isSendRsmq;

    private String acceptStatus;

    private String acceptFailReason;

    private String transStatus;

    private String businessType;

    private String serviceType;

    private String smsPhone;

    private String mchManager;

    private String mchManagerDept;

    private Integer splitCount;

    private String templateCode;

    private String templateSign;

    private Date createTime;

    private Date modifyTime;

    private String remark;

    private String jsonRemark;

    public String getMchOrderNo() {
        return mchOrderNo;
    }

    public void setMchOrderNo(String mchOrderNo) {
        this.mchOrderNo = mchOrderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getChannelTransId() {
        return channelTransId;
    }

    public void setChannelTransId(String channelTransId) {
        this.channelTransId = channelTransId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getSpFee() {
        return spFee;
    }

    public void setSpFee(BigDecimal spFee) {
        this.spFee = spFee;
    }

    public BigDecimal getIpaynowAmount() {
        return ipaynowAmount;
    }

    public void setIpaynowAmount(BigDecimal ipaynowAmount) {
        this.ipaynowAmount = ipaynowAmount;
    }

    public String getSettleFlag() {
        return settleFlag;
    }

    public void setSettleFlag(String settleFlag) {
        this.settleFlag = settleFlag;
    }

    public String getChannelTransDate() {
        return channelTransDate;
    }

    public void setChannelTransDate(String channelTransDate) {
        this.channelTransDate = channelTransDate;
    }

    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getIsTransfered() {
        return isTransfered;
    }

    public void setIsTransfered(String isTransfered) {
        this.isTransfered = isTransfered;
    }

    public String getIsSendRsmq() {
        return isSendRsmq;
    }

    public void setIsSendRsmq(String isSendRsmq) {
        this.isSendRsmq = isSendRsmq;
    }

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getAcceptFailReason() {
        return acceptFailReason;
    }

    public void setAcceptFailReason(String acceptFailReason) {
        this.acceptFailReason = acceptFailReason;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSmsPhone() {
        return smsPhone;
    }

    public void setSmsPhone(String smsPhone) {
        this.smsPhone = smsPhone;
    }

    public String getMchManager() {
        return mchManager;
    }

    public void setMchManager(String mchManager) {
        this.mchManager = mchManager;
    }

    public String getMchManagerDept() {
        return mchManagerDept;
    }

    public void setMchManagerDept(String mchManagerDept) {
        this.mchManagerDept = mchManagerDept;
    }

    public Integer getSplitCount() {
        return splitCount;
    }

    public void setSplitCount(Integer splitCount) {
        this.splitCount = splitCount;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateSign() {
        return templateSign;
    }

    public void setTemplateSign(String templateSign) {
        this.templateSign = templateSign;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getJsonRemark() {
        return jsonRemark;
    }

    public void setJsonRemark(String jsonRemark) {
        this.jsonRemark = jsonRemark;
    }
}