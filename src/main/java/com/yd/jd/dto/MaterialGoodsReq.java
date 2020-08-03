package com.yd.jd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 查询物料商品 请求入参
 */
@Data
@NoArgsConstructor
public class MaterialGoodsReq {
    @NonNull
    //频道id：1-好券商品,2-超级大卖场,10-9.9专区,22-热销爆品,23-为你推荐,24-数码家电,
    // 25-超市,26-母婴玩具,27-家具日用,28-美妆穿搭,29-医药保健,30-图书文具,31-今日必推,
    // 32-品牌好货,33-秒杀商品,34-拼购商品,40-高收益,41-自营热卖榜,109-新品首发,110-自营,
    // 125-首购商品,129-高佣榜单,130-视频商品
    private int eliteId;
    private int pageIndex;
    private int pageSize;
    private String sortName; //排序字段(price：单价, commissionShare：佣金比例, commission：佣金， inOrderCount30DaysSku：sku维度30天引单量，comments：评论数，goodComments：好评数)
    private String sort; //asc,desc升降序,默认降序
    private String subUnionId; //子联盟ID（需申请，申请方法请见https://union.jd.com/helpcenter/13246-13247-46301），该字段为自定义参数，建议传入字母数字和下划线的格式
    private String siteId; //站点ID是指在联盟后台的推广管理中的网站Id、APPID（1、通用转链接口禁止使用社交媒体id入参；2、订单来源，即投放链接的网址或应用必须与传入的网站ID/AppID备案一致，否则订单会判“无效-来源与备案网址不符”）
    private String positionId; //推广位id
    private String ext1; //系统扩展参数，无需传入
    private String skuId; //商品ID，用于推荐相似商品
    private int hasCoupon; //1：只查询有最优券商品，不传值不做限制
    private int userIdType; //用户ID类型，传入此参数可获得个性化推荐结果，32：苹果移动设备idfa，32768：android oaid
    private String userId; //userIdType对应的用户设备ID，传入此参数可获得个性化推荐结果
    private String fields; //支持出参数据筛选，逗号','分隔，目前可用：videoInfo,documentInfo
}
