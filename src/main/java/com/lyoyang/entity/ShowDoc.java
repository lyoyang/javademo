package com.lyoyang.entity;

import lombok.Data;

import java.util.List;

@Data
public class ShowDoc {

    /**
     * 是否上传show doc
     */
    private Boolean isOpenLocal;
    /**
     * show doc部署地址
     */
    private String showDocUrl;
    /**
     * show doc open api key
     */
    private String apiKey;
    /**
     * show doc open api token
     */
    private String apiToken;
    /**
     * markdown文档信息
     */
    private List<ShowDocModel> showDocModels;

}
