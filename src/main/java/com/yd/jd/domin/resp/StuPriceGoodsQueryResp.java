package com.yd.jd.domin.resp;

import java.io.Serializable;

/**
 * 学生价商品
 */
public class StuPriceGoodsQueryResp implements Serializable {
    private String skuName; //商品名称
    private long skuId; //商品id
    private String imageUrl; //图片url
    private int isStuPrice; //是否学生价商品。 1：是学生价商品。 0：不是学生价商品。
    private double jdPrice; //京东价
    private double studentPrice; //学生专享价
    private long stuPriceStartTime; //专享价促销开始时间（时间戳：毫秒）
    private long stuPriceEndTime; //专享价促销结束时间（时间戳：毫秒）
    private long cid1Id; //一级类目id
    private long cid2Id; //二级类目id
    private long cid3Id; //三级类目id
    private String cid1Name; //一级类目名称
    private String cid2Name; //二级类目名称
    private String cid3Name; //三级类目名称
    private double commissionShare; //通用佣金比例，百分比
    private double commission; //通用佣金
    private String owner; //是否自营。g=自营，p=pop
    private long inOrderCount30Days; //30天引入订单量（spu）
    private double inOrderComm30Days; //30天支出佣金（spu）
}
