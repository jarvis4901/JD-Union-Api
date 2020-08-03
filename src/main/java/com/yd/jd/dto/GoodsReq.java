package com.yd.jd.dto;

import lombok.Data;

/**
 * 商品查询 业务参数 请求入参
 */
@Data
public class GoodsReq {
    private Long cid1; //一级类目id
    private Long cid2; //二级类目id
    private Long cid3; //三级类目id
    private Integer pageIndex; //页码
    private Integer pageSize; //每页数量，单页数最大30，默认20
    private Long[] skuIds; //skuid集合(一次最多支持查询100个sku)，数组类型开发时记得加[] eg: 5225346,7275691
    private String keyword; //关键词，字数同京东商品名称一致，目前未限制
    private Double pricefrom; //商品价格下限
    private Double priceto; //商品价格上限
    private Integer commissionShareStart; //佣金比例区间开始
    private Integer commissionShareEnd; //佣金比例区间结束
    private String owner; //商品类型：自营[g]，POP[p]
    private String sortName; //排序字段(price：单价, commissionShare：佣金比例, commission：佣金， inOrderCount30Days：30天引单量， inOrderComm30Days：30天支出佣金)
    private String sort; //asc,desc升降序,默认降序
    private Integer isCoupon; //是否是优惠券商品，1：有优惠券，0：无优惠券
    private Integer isPG; //是否是拼购商品，1：拼购商品，0：非拼购商品
    private Double pingouPriceStart; //拼购价格区间开始
    private Double pingouPriceEnd; //拼购价格区间结束
    private Integer isHot; //是否是爆款，1：爆款商品，0：非爆款商品
    private String brandCode; //品牌code
    private Integer shopId; //店铺Id
    private Integer hasContent; //1：查询内容商品；其他值过滤掉此入参条件。
    private Integer hasBestCoupon; //1：查询有最优惠券商品；其他值过滤掉此入参条件。
    private String pid; //联盟id_应用iD_推广位id
    private String fields; //支持出参数据筛选，逗号','分隔，目前可用：videoInfo(视频信息),commentInfo(评论信息),hotWords(热词),similar(相似推荐商品),documentInfo(段子信息)
    private String forbidTypes; //过滤规则，入参表示不展示该规则数据，支持多个逗号','分隔，2:OTC商品;3:加油卡;4:游戏充值卡;5:合约机;6:京保养;7:虚拟组套;8:定制商品
    private Integer[] jxFlags; //京喜商品类型，1京喜、2京喜工厂直供、3京喜优选（包含3时可在京东APP购买），入参多个值表示或条件查询 [1,2,3]
    private Double shopLevelFrom; //支持传入0.0、2.5、3.0、3.5、4.0、4.5、4.9，默认为空表示不筛选评分
}
