package com.yd.jd.domin.resp;


import lombok.Data;

@Data
public class ActivityQueryResp {
    private String urlPC; //活动落地页-pc
    private String urlM; //活动落地页-m
    private String title; //活动主题
    private String advantage; //活动利益点
    private String startTime; //活动开始时间
    private String endTime; //活动结束时间
    private String eliteId; //资源位id
    private String recommend; //推荐指数
    private String downloadUrl; //活动素材下载链接
    private String downloadCode; //活动素材提取码
}
