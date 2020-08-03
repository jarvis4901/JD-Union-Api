package com.yd.jd.vo;

import com.yd.jd.domin.resp.GoodsDetailQueryResp;
import lombok.Data;

@Data
public class GoodsDetailQueryResult {
    private int code;
    private String message;
    private GoodsDetailQueryResp[] data;
}
