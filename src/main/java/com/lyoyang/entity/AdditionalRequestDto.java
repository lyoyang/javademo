package com.lyoyang.entity;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 信息完善dto
 */
@Data
public class AdditionalRequestDto {


    /**
     * 公司名称
     */
    @NotEmpty(message = "companyName不能为空")
    public String companyName;

    /**
     * 所属行业
     */
    @NotEmpty(message = "industryId不能为空")
    public Long industryId;

    /**
     * 统一社会信用代码
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String unifiedSocialCreditCode;

    /**
     * 注册资金
     */
    @NotEmpty(message = "registeredCapital不能为空")
    public String registeredCapital;

    /**
     * 注册地址
     */
    @NotEmpty(message = "registeredAddress不能为空")
    public String registeredAddress;

    /**
     * 经营地址
     */
    @NotEmpty(message = "businessAddress不能为空")
    public String businessAddress;

    /**
     * 联系电话
     */
    @NotEmpty(message = "companyTelephone不能为空")
    public String companyTelephone;

    /**
     * 联系邮箱
     */
    @NotEmpty(message = "companyEmail不能为空")
    public String companyEmail;

    /**
     * 营业执照
     */
    @NotEmpty(message = "businessLicense不能为空")
    public String businessLicense;

    /*
     * 法人姓名
     */
    @NotEmpty(message = "legalName不能为空")
    public String legalName;

    /**
     * 证件类型
     */
    @NotEmpty(message = "legalCertificateType不能为空")
    public String legalCertificateType;

    /**
     * 证件号码
     */
    @NotEmpty(message = "legalCertificateCode不能为空")
    public String legalCertificateCode;

    /**
     * 证件有效期
     */
    @NotEmpty(message = "legalCertificateValidity不能为空")
    public String legalCertificateValidity;

    /**
     * 证件照正面
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String legalCertificateFront;


    /**
     * 证件照反面
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String legalCertificateBackend;

    /**
     * 经办人姓名
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String operatorName;

    /**
     * 证件类型
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String operatorCertificateType;

    /**
     * 证件号码
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String operatorCertificateCode;

    /**
     * 证件有效期
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String operatorCertificateValidity;

    /**
     * 职务
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String operatorPosition;

    /**
     * 手机号
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String operatorPhone;

    /**
     * 邮箱
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String operatorEmail;

    /**
     * 法人授权书
     */
    @NotEmpty(message = "unifiedSocialCreditCode不能为空")
    public String legalAuthorization;



}
