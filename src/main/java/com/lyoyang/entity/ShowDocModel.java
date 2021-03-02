package com.lyoyang.entity;


import lombok.Data;

@Data
public class ShowDocModel {

    /**
     * 标题
     */
    private String folder;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否encode
     */
    private boolean encode = true;

}
