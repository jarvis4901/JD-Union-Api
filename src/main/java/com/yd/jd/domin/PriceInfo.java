package com.yd.jd.domin;

import lombok.Data;

/**
 * 价格信息
 */
@Data
public class PriceInfo {
    private double price; //无线价格
    private double lowestPrice; //最低价格
    private int lowestPriceType; //最低价格类型，1：无线价格；2：拼购价格； 3：秒杀价格
    private double lowestCouponPrice; //最低价后的优惠券价最低价后的优惠券价(当商品无最优券时，不返回该字段)
    private int historyPriceDay; //历史最低价天数
}
