package com.lyoyang.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ipaynow
 * Date: 16-12-26
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public class CalculationDto implements Serializable {
    private String transId;
    private Date transDate;
    private String mchId;
    private String settleAccId;
    private String deviceId;
    private String channelId;
    private String transStatus;
    private String transType;
    private String orgId;
    private String currency;
    private String nation;
    private BigDecimal amount;
    private BigDecimal fee;
    private BigDecimal settleAmount;
    private BigDecimal rebateAmount;
    private BigDecimal orgAmount;
    private BigDecimal ipaynowAmount;
    private BigDecimal spFee;
    private String accDate;
    private String spMid;
    private String subMchId;
    private String originalTransId;
    private Date originalTransDate;
    private String mhtSubMchId;
    private String remark;
    private String routerTypeId;
    private String channelTransId;
    private String cardType;
    private String cardBrand;
    private String dccMode;
    private String storeId;
    private BigDecimal bankCost;
    private BigDecimal agentServiceFee;
    private BigDecimal bankRebateIpaynow;
    private BigDecimal bankProfit;
    private BigDecimal ratio;
    private BigDecimal rabateAmount;
    private String feeStrategy;
    private BigDecimal subMchFee;
    private String settleDate;
    private BigDecimal settleAmountChannel;
    private BigDecimal originalAmount;
    private String realMchId;
    private BigDecimal vipTotalSharedAmount;
    private BigDecimal bankRebateMch;
    private BigDecimal chRealDiscountAmount;
    private BigDecimal chMchDiscountAmount;
    private BigDecimal chDiscountAmount;
    private String mchModel;
    private BigDecimal govAmount;
    private BigDecimal agentAmount;
    private String isSendProfit;
    private String errorReasonType;
    private BigDecimal ipaynowRebateMch;
    private BigDecimal feeChannel;
    private String isProfitShare;
    private String subDeviceId;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public BigDecimal getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(BigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public String getSettleAccId() {
        return settleAccId;
    }

    public void setSettleAccId(String settleAccId) {
        this.settleAccId = settleAccId;
    }

    public String getMhtSubMchId() {
        return mhtSubMchId;
    }

    public void setMhtSubMchId(String mhtSubMchId) {
        this.mhtSubMchId = mhtSubMchId;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public BigDecimal getOrgAmount() {
        return orgAmount;
    }

    public void setOrgAmount(BigDecimal orgAmount) {
        this.orgAmount = orgAmount;
    }

    public BigDecimal getIpaynowAmount() {
        return ipaynowAmount;
    }

    public void setIpaynowAmount(BigDecimal ipaynowAmount) {
        this.ipaynowAmount = ipaynowAmount;
    }

    public BigDecimal getSpFee() {
        return spFee;
    }

    public void setSpFee(BigDecimal spFee) {
        this.spFee = spFee;
    }

    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }

    public String getSpMid() {
        return spMid;
    }

    public void setSpMid(String spMid) {
        this.spMid = spMid;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRouterTypeId() {
        return routerTypeId;
    }

    public void setRouterTypeId(String routerTypeId) {
        this.routerTypeId = routerTypeId;
    }

    public String getChannelTransId() {
        return channelTransId;
    }

    public void setChannelTransId(String channelTransId) {
        this.channelTransId = channelTransId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getDccMode() {
        return dccMode;
    }

    public void setDccMode(String dccMode) {
        this.dccMode = dccMode;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getOriginalTransId() {
        return originalTransId;
    }

    public void setOriginalTransId(String originalTransId) {
        this.originalTransId = originalTransId;
    }

    public Date getOriginalTransDate() {
        return originalTransDate;
    }

    public void setOriginalTransDate(Date originalTransDate) {
        this.originalTransDate = originalTransDate;
    }


    public BigDecimal getBankCost() {
        return bankCost;
    }

    public void setBankCost(BigDecimal bankCost) {
        this.bankCost = bankCost;
    }

    public BigDecimal getAgentServiceFee() {
        return agentServiceFee;
    }

    public void setAgentServiceFee(BigDecimal agentServiceFee) {
        this.agentServiceFee = agentServiceFee;
    }

    public BigDecimal getBankRebateIpaynow() {
        return bankRebateIpaynow;
    }

    public void setBankRebateIpaynow(BigDecimal bankRebateIpaynow) {
        this.bankRebateIpaynow = bankRebateIpaynow;
    }

    public BigDecimal getBankProfit() {
        return bankProfit;
    }

    public void setBankProfit(BigDecimal bankProfit) {
        this.bankProfit = bankProfit;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getRabateAmount() {
        return rabateAmount;
    }

    public void setRabateAmount(BigDecimal rabateAmount) {
        this.rabateAmount = rabateAmount;
    }

    public String getFeeStrategy() {
        return feeStrategy;
    }

    public void setFeeStrategy(String feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public BigDecimal getSubMchFee() {
        return subMchFee;
    }

    public void setSubMchFee(BigDecimal subMchFee) {
        this.subMchFee = subMchFee;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public BigDecimal getSettleAmountChannel() {
        return settleAmountChannel;
    }

    public void setSettleAmountChannel(BigDecimal settleAmountChannel) {
        this.settleAmountChannel = settleAmountChannel;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getRealMchId() {
        return realMchId;
    }

    public void setRealMchId(String realMchId) {
        this.realMchId = realMchId;
    }

    public BigDecimal getVipTotalSharedAmount() {
        return vipTotalSharedAmount;
    }

    public void setVipTotalSharedAmount(BigDecimal vipTotalSharedAmount) {
        this.vipTotalSharedAmount = vipTotalSharedAmount;
    }

    public BigDecimal getBankRebateMch() {
        return bankRebateMch;
    }

    public void setBankRebateMch(BigDecimal bankRebateMch) {
        this.bankRebateMch = bankRebateMch;
    }

    public BigDecimal getChRealDiscountAmount() {
        return chRealDiscountAmount;
    }

    public void setChRealDiscountAmount(BigDecimal chRealDiscountAmount) {
        this.chRealDiscountAmount = chRealDiscountAmount;
    }

    public BigDecimal getChMchDiscountAmount() {
        return chMchDiscountAmount;
    }

    public void setChMchDiscountAmount(BigDecimal chMchDiscountAmount) {
        this.chMchDiscountAmount = chMchDiscountAmount;
    }

    public BigDecimal getChDiscountAmount() {
        return chDiscountAmount;
    }

    public void setChDiscountAmount(BigDecimal chDiscountAmount) {
        this.chDiscountAmount = chDiscountAmount;
    }

    public String getMchModel() {
        return mchModel;
    }

    public void setMchModel(String mchModel) {
        this.mchModel = mchModel;
    }

    public BigDecimal getGovAmount() {
        return govAmount;
    }

    public void setGovAmount(BigDecimal govAmount) {
        this.govAmount = govAmount;
    }

    public BigDecimal getAgentAmount() {
        return agentAmount;
    }

    public void setAgentAmount(BigDecimal agentAmount) {
        this.agentAmount = agentAmount;
    }

    public String getSendProfit() {
        return isSendProfit;
    }

    public void setIsSendProfit(String sendProfit) {
        isSendProfit = sendProfit;
    }

    public String getErrorReasonType() {
        return errorReasonType;
    }

    public void setErrorReasonType(String errorReasonType) {
        this.errorReasonType = errorReasonType;
    }

    public BigDecimal getIpaynowRebateMch() {
        return ipaynowRebateMch;
    }

    public void setIpaynowRebateMch(BigDecimal ipaynowRebateMch) {
        this.ipaynowRebateMch = ipaynowRebateMch;
    }

    public BigDecimal getFeeChannel() {
        return feeChannel;
    }

    public void setFeeChannel(BigDecimal feeChannel) {
        this.feeChannel = feeChannel;
    }

    public String getIsProfitShare() {
        return isProfitShare;
    }

    public void setIsProfitShare(String isProfitShare) {
        this.isProfitShare = isProfitShare;
    }

    public String getSubDeviceId() {
        return subDeviceId;
    }

    public void setSubDeviceId(String subDeviceId) {
        this.subDeviceId = subDeviceId;
    }
}
