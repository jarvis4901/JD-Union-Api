package com.yd.jd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

/**
 * 京粉商品 业务参数 请求入参
 */
@Data
@NoArgsConstructor
public class JFGoodsReq implements Serializable {
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
    private String pid;
    private String fields; //支持出参数据筛选，逗号','分隔，目前可用：videoInfo,documentInfo
}
