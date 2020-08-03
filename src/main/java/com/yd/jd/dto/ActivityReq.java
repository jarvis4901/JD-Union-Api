package com.yd.jd.dto;

import lombok.Data;

@Data
public class ActivityReq {
    private int pageIndex; //页面默认1；
    private int pageSize; //每页数量默认20,上限50
}
