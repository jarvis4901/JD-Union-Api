package com.yd.jd.dto;

import lombok.Data;

/**
 * 奖励订单
 */
@Data
public class BonusOrderReq {
    private  int optType; //时间类型 (1：下单时间，sortValue和pageSize组合使用； 2：更新时间，pageNo和pageSize组合使用)
    private  long startTime; //订单开始时间，时间戳（毫秒），起止时间限制10min内
    private  long endTime; //订单结束时间，时间戳（毫秒），起止时间限制10min内
    private  int pageNo; //页码，默认值为1
    private  int pageSize; //每页数量，上限100
    private  String sortValue; //时间类型按'下单'查询时，和pageSize组合使用。获取最后一条记录的sortValue，指定拉取条数（pageSize），以此方式查询数据。

}
