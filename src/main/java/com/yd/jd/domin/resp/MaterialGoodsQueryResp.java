package com.yd.jd.domin.resp;

import com.yd.jd.domin.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class MaterialGoodsQueryResp implements Serializable {
    private long skuId; //商品ID
    private String skuName; //商品名称
    private int isHot; //是否爆款，1：是，0：否
    private long spuid; //spuid，其值为同款商品的主skuid
    private String brandCode; //品牌名
    private String owner; //g=自营，p=pop
    private CategoryInfo categoryInfo;
    private CommissionInfo commissionInfo;
    private CouponInfo couponInfo;
    private long comments; //评论数
    private ImageInfo imageInfo;
    private double goodCommentsShare; //商品好评率
    private PriceInfo priceInfo;
    private PinGouInfo pinGouInfo;
    private ResourceInfo resourceInfo;
    private SeckillInfo seckillInfo;
    private int[] jxFlags; //京喜商品类型，1京喜、2京喜工厂直供、3京喜优选
    private VideoInfo videoInfo;
    private PromotionInfo promotionInfo; //推广信息
    private long inOrderCount30DaysSku;  //30天引单数量(sku维度)
    private long inOrderCount30Days; //30天引单数量
}
