package com.yd.jd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * 订单查询 请求入参
 */
@Data
@NoArgsConstructor
public class OrderReq implements Serializable {
    private int pageNo;
    private int pageSize;
    private int type; //订单时间查询类型(1：下单时间，2：完成时间，3：更新时间)
    private String time; //查询时间，建议使用分钟级查询，格式：yyyyMMddHH、yyyyMMddHHmm或yyyyMMddHHmmss，如201811031212 的查询范围从12:12:00--12:12:59
    private long childUnionId; //子站长ID（需要联系运营开通PID账户权限才能拿到数据），childUnionId和key不能同时传入
    private String key; //其他推客的授权key，查询工具商订单需要填写此项，childUnionid和key不能同时传入

}

