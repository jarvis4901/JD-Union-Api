//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yd.jd.domin.resp;

import java.io.Serializable;

import lombok.Data;

/**
 * 促销商品
 */
@Data
public class PromotionGoodsResp implements Serializable {
    private long skuId;
    private double unitPrice;
    private String materialUrl;
    private long endDate;
    private int isFreeFreightRisk;
    private int isFreeShipping;
    private double commisionRatioWl;
    private double commisionRatioPc;
    private String imgUrl;
    private long vid;
    private String cidName;
    private double wlUnitPrice;
    private String cid2Name;
    private int isSeckill;
    private long cid2;
    private String cid3Name;
    private long inOrderCount;
    private long cid3;
    private long shopId;
    private int isJdSale;
    private String goodsName;
    private long startDate;
    private long cid;
}
