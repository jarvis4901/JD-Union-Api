package com.yd.jd.domin;

import lombok.Data;

/**
 * 优惠券
 */
@Data
public class Coupon {
    private int bindType; //券种类 (优惠券种类：0 - 全品类，1 - 限品类（自营商品），2 - 限店铺，3 - 店铺限商品券)
    private double discount; //券面额
    private String link; //http://coupon.jd.com/ilink/couponActiveFront/front_index.action?XXXXXXX  券链接
    private int platformType; //券使用平台 (平台类型：0 - 全平台券，1 - 限平台券)
    private double quota; //券消费限额
    private long getStartTime; //领取开始时间(时间戳，毫秒)
    private long getEndTime; //券领取结束时间(时间戳，毫秒)
    private long useStartTime; //券有效使用开始时间(时间戳，毫秒)
    private long useEndTime; //券有效使用结束时间(时间戳，毫秒)
    private int isBest; //最优优惠券，1：是；0：否
    private int hotValue; //券热度，值越大热度越高，区间:[0,10]
}
