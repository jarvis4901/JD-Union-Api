package com.yd.jd.domin;

import lombok.Data;

/**
 * 影音大字段信息
 */
@Data
public class VideoBigFieldInfo {
    private String comments; //评论
    private String image; //商品描述(精彩剧照)
    private String contentDesc; //内容摘要(内容简介)
    private String editerDesc; //编辑推荐
    private String catalogue; //目录
    private String box_Contents; //包装清单
    private String material_Description; //特殊说明
    private String manual; //说明书
    private String productFeatures; //产品特色
}
