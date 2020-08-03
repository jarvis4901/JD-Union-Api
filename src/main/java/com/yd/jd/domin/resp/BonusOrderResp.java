package com.yd.jd.domin.resp;

import lombok.Data;

/**
 * 奖励订单
 */
@Data
public class BonusOrderResp {
    private long unionId;
    private String bonusInvalidCode; //无效状态码，-1:无效、2:无效-拆单、3:无效-取消、4:无效-京东帮帮主订单、5:无效-账号异常、6:无效-赠品类目不返佣 等
    private String bonusInvalidText; //无效状态码对应的无效状态文案
    private double payPrice; //实际支付金额
    private double estimateCosPrice; //预估计佣金额
    private double estimateFee; //预估佣金
    private double actualCosPrice; //实际计佣金额
    private double actualFee; //实际佣金
    private long orderTime; //下单时间、时间戳（毫秒）
    private long finishTime; //完成时间、时间戳（毫秒）
    private long positionId; //推广位ID
    private long orderId; //订单号
    private int bonusState; //奖励状态，0:无效、1:有效
    private String bonusText; //奖励状态文案
    private String skuName; //商品名称
    private float commissionRate; //佣金比例，单位：%
    private String subUnionId; //子联盟ID
    private String pid; //pid
    private String ext1; //推客生成推广链接时传入的扩展字段
    private String unionAlias; //母站长简称
    private double subSideRate; //分成比例，单位：%
    private double subsidyRate; //补贴比例，单位：%
    private double finalRate; //最终比例，单位：%
    private String activityName; //活动名称
    private long parentId; //parentId
    private long skuId; //skuId
    private double estimateBonusFee; //预估奖励金额
    private double actualBonusFee; //实际奖励金额
    private double orderState; //订单奖励状态，1:已完成、2:已付款、3:待付款
    private String orderText; //订单奖励状态文本
    private String sortValue; //排序值，按'下单时间'分页查询时使用
}
