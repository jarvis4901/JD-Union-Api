package com.yd.jd.domin.resp;

import lombok.Data;

@Data
public class CouponResp {
    private long takeEndTime; //券领取结束时间(时间戳，毫秒)
    private long takeBeginTime; //券领取开始时间(时间戳，毫秒)
    private long remainNum; //券剩余张数
    private String yn; //券有效状态
    private long num; //券总张数
    private double quota; //券消费限额
    private String link; //券链接
    private double discount; //券面额
    private long beginTime; //券有效使用开始时间(时间戳，毫秒)
    private long endTime; //券有效使用结束时间(时间戳，毫秒)
    private String platform; //券使用平台
}
