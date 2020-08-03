package com.yd.jd.domin.resp;

import lombok.Data;

/**
 * 礼金数据统计
 */
@Data
public class GiftCouponQueryResp {
    private String giftCouponKey; //礼金批次ID
    private long amount; //创建数量
    private long receiveNum; //领取/发放数量
    private long costNum; //使用数量
    private double ygCommission; //预估佣金
    private int status; //礼金状态（1：正常，2：停止）
    private String receiveStartTime; //领取开始日期（yyyy-MM-dd HH:mm:ss）
    private String receiveEndTime; //领取结束日期（yyyy-MM-dd HH:mm:ss）
    private int effectiveDays; //使用 有效期/天数
    private String costAmount; //使用资金，实际花费金额,,订单中使用的券的面额总和
    private int type; //礼金类型（官方：1，推客：2）
    private long[] skuIdList; //skuIdList
    private int share; //礼金是否允许分享领取：-1允许，1不允许
}
