package com.yd.jd.dto;

import lombok.Data;

/**
 * 商品详情 请求入参
 */
@Data
public class BigFieldGoodsReq {
    private long[] skuIds; //skuId集合
    private String[] fields; //查询域集合，不填写则查询全部
}
