package com.yd.jd.vo;

import com.yd.jd.domin.resp.GoodsResp;
import lombok.Data;

import java.io.Serializable;

/**
 * 关键词商品查询接口返回结果
 */
@Data
public class GoodsQueryResult implements Serializable {
    private int code;
    private String message;
    private GoodsResp[] data;
    private long totalCount; //有效商品总数量
    private String hotWords; //日常top10的热搜词，按小时更新 eg:牛奶,家电
    private Long[] similarSkuList; //相似推荐商品skuId集合 eg:[11144230,11993134]
}