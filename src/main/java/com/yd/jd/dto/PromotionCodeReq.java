package com.yd.jd.dto;

import lombok.Data;

/**
 * 推广链接信息 请求入参
 */
@Data
public class PromotionCodeReq {
    private String materialId; //推广物料 eg:https://item.jd.com/23484023378.html
    private long unionId; //目标推客的联盟ID
    private String siteId; //站点ID是指在联盟后台的推广管理中的网站Id、APPID（1、通用转链接口禁止使用社交媒体id入参；2、订单来源，即投放链接的网址或应用必须与传入的网站ID/AppID备案一致，否则订单会判“无效-来源与备案网址不符”）
    private long positionId; //推广位id
    private String subUnionId; //子联盟ID（需申请，申请方法请见https://union.jd.com/helpcenter/13246-13247-46301），该字段为自定义参数，建议传入字母数字和下划线的格式
    private String ext1; //系统扩展参数，无需传入
    //    private int protocol; //推广物料 eg:https://item.jd.com/23484023378.html  已废弃
    private String pid; //联盟子站长身份标识，格式：子站长ID_子站长网站ID_子站长推广位ID
    private String couponUrl; //优惠券领取链接，在使用优惠券、商品二合一功能时入参，且materialId须为商品详情页链接
    private String giftCouponKey; //礼金批次号
}
