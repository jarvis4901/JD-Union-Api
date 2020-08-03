package com.yd.jd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 秒杀商品 请求入参
 */
@Data
@NoArgsConstructor
public class SecKillGoodsReq implements Serializable {
    private long[] skuIds; //sku id集合，长度最大30 eg：2622752,2112918
    private int pageIndex; //页码，默认1
    private int pageSize; //每页数量最大30，默认30
    private int isBeginSecKill; //是否返回未开始秒杀商品。1=返回，0=不返回
    private double secKillPriceFrom; //秒杀价区间开始（单位：元）
    private double secKillPriceTo; //秒杀价区间结束
    private long cid1; //一级类目
    private long cid2; //二级类目
    private long cid3; //三级类目
    private String owner; //g=自营，p=pop
    private double commissionShareFrom; //佣金比例区间开始
    private double commissionShareTo; //佣金比例区间结束
    private String sortName; //排序字段，可为空。 （默认搜索综合排序。允许的排序字段：seckillPrice、commissionShare、inOrderCount30Days、inOrderComm30Days）
    private String sort; //desc=降序，asc=升序，可为空（默认降序）
}
