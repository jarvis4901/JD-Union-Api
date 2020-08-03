package com.yd.jd.dto;


import lombok.Data;

/**
 * 学生价商品 请求入参
 */
@Data
public class StuPriceGoodsReq {
    private long[] skuIds; //sku id集合，长度最大30 eg：2622752,2112918
    private int pageIndex; //页码，默认1
    private int pageSize; //每页数量最大30，默认30
    private double stuPriceFrom; //学生专享价区间开始（单位：元）
    private double stuPriceTo; //学生专享价区间结束（单位：元）
    private long cid1; //一级类目
    private long cid2; //二级类目
    private long cid3; //三级类目
    private String owner; //g=自营，p=pop
    private double commissionShareFrom; //佣金比例区间开始
    private double commissionShareTo; //佣金比例区间结束
    private String sortName; //排序字段，默认搜索综合排序。允许的排序字段：stuPrice、commissionShare、inOrderCount30Days、inOrderComm30Days
    private String sort; //desc=降序，asc=升序，可为空（默认降序）
}
