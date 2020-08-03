package com.yd.jd.domin;

import lombok.Data;

/**
 * 图书大字段信息
 */
@Data
public class BookBigFieldInfo {
    private String comments; //媒体评论
    private String image; //精彩文摘与插图(插图)
    private String contentDesc; //内容摘要(内容简介)
    private String relatedProducts; //产品描述(相关商品)
    private String editerDesc; //编辑推荐
    private String catalogue; //目录
    private String authorDesc; //作者简介
    private String bookAbstract; //精彩摘要(精彩书摘)
    private String introduction; //前言(前言/序言)
    private String productFeatures; //产品特色
}
