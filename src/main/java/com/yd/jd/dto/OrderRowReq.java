package com.yd.jd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRowReq {
    private int pageIndex; //页码
    private int pageSize; //每页包含条数，上限为500 默认20
    private int type; //页订单时间查询类型(1：下单时间，2：完成时间，3：更新时间)
    private String startTime; //开始时间 格式yyyy-MM-dd HH:mm:ss，与endTime间隔不超过1小时
    private String endTime; //结束时间 格式yyyy-MM-dd HH:mm:ss，与startTime间隔不超过1小时
    private long childUnionId; //子站长ID（需要联系运营开通PID账户权限才能拿到数据），childUnionId和key不能同时传入
    private String key; //其他推客的授权key，查询工具商订单需要填写此项，childUnionid和key不能同时传入
    private String fields; //出参数据筛选，多项逗号分隔
}
