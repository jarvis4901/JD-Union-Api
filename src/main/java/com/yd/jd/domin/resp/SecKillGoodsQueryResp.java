package com.yd.jd.domin.resp;

import lombok.Data;

/**
 * 秒杀商品
 */
@Data
public class SecKillGoodsQueryResp {
    private String skuName; //商品名称
    private long skuId; //商品id
    private String imageUrl; //图片url
    private int isSecKill; //是秒杀。1：是商品 0：非秒杀商品
    private double oriPrice; //原价
    private double secKillPrice; //秒杀价
    private long secKillStartTime; //秒杀开始展示时间（时间戳：毫秒）
    private long secKillEndTime; //秒杀结束时间（时间戳：毫秒）
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
    private double jdPrice; //京东价
}
