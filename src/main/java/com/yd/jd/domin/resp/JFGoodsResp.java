package com.yd.jd.domin.resp;

import com.yd.jd.domin.*;
import lombok.Data;

/**
 * 京粉商品
 */
@Data
public class JFGoodsResp {
    private CategoryInfo categoryInfo; //类目信息
    private long comments; //评论数
    private CommissionInfo commissionInfo;  //佣金信息
    private CouponInfo couponInfo; //优惠券信息，返回内容为空说明该SKU无可用优惠券
    private double goodCommentsShare; //商品好评率
    private ImageInfo imageInfo; //图片信息
    private long inOrderCount30Days; //30天引单数量
    private String materialUrl; //商品落地页 item.jd.com/26898778009.html
    private PriceInfo priceInfo; //价格信息
    private ShopInfo shopInfo; //店铺信息；
    private long skuId; //商品id
    private String skuName; //商品名称
    private int isHot; //是否爆款，1：是，0：否
    private long spuid; //spuid，其值为同款商品的主skuid
    private String brandCode; //品牌code
    private String brandName; //品牌名
    private String owner; //g=自营，p=pop
    private PinGouInfo pinGouInfo; //拼购信息
    private ResourceInfo resourceInfo; //资源信息
    private long inOrderCount30DaysSku; //30天引单数量(sku维度)
    private SeckillInfo seckillInfo; //秒杀信息
    private Integer[] jxFlags; //京喜商品类型，1京喜、2京喜工厂直供、3京喜优选（包含3时可在京东APP购买）
    private VideoInfo videoInfo; //视频信息
    private DocumentInfo documentInfo; //段子信息
}
