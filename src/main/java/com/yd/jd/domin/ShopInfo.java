package com.yd.jd.domin;

import lombok.Data;

/**
 * 店铺信息
 */
@Data
public class ShopInfo {
    private String shopName; //店铺名称（或供应商名称）
    private long shopId; //店铺名称（或供应商名称）
    private Double shopLevel; //店铺评分
}
