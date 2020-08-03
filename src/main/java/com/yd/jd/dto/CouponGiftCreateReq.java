package com.yd.jd.dto;

import lombok.Data;

@Data
public class CouponGiftCreateReq {
    private String skuMaterialId; // 商品skuId或落地页地址
    private double discount; // 优惠券面额
    private int amount; // 总数量
    private String receiveStartTime; // 领取开始时间(yyyy-MM-dd HH)，区间为(当前时间天内,当天+6天)，系统补充为yyyy-MM-dd HH:00:00
    private String receiveEndTime; // 领取结束时间(yyyy-MM-dd HH)，区间为(当前时间之后,当天+6天)，系统补充为yyyy-MM-dd HH:59:59
    private int effectiveDays; // 领取生效天数1至7
    private int isSpu; // 是否绑定同spu商品(1:是;0:否)
    private int expireType; // 时间类型：1.相对时间，2.绝对时间
    private String useStartTime; //使用开始时间，格式：yyyy-MM-dd
    private String useEndTime; // 使用结束时间，格式：yyyy-MM-dd
    private int share; // 每个链接是否限制礼金领取次数：-1不限领取次数，1只能领取一次
}
