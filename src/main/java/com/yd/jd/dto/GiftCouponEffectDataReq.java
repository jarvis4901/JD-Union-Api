package com.yd.jd.dto;

import lombok.Data;

/**
 * 礼金效果统计 请求入参
 */
@Data
public class GiftCouponEffectDataReq {
    private long skuId; //skuId
    private String giftCouponKey; //礼金批次
    private String createTime; //创建日期（精确到天），yyyy-MM-dd
    private String startTime; //下单日期，yyyy-MM-dd
}
