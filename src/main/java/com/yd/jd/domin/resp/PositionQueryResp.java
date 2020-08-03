package com.yd.jd.domin.resp;

import lombok.Data;

@Data
public class PositionQueryResp {
    private int pageNo; //
    private int pageSize;
    private PositionResp[] result;
    private long total;
}
