package com.yd.jd.vo;

import lombok.Data;

/**
 * 根据skuid查询商品信息接口
 * 商品类目查询接口
 * 商品详情查询接口
 * 优惠券领取情况查询接口
 * 奖励订单查询接口
 */
@Data
public class SimpleListResult<T> {
    private int code;
    private String message;
    private T[] data;
}
