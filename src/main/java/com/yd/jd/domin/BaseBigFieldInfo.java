package com.yd.jd.domin;

import lombok.Data;

/**
 * 基础大字段信息
 */
@Data
public class BaseBigFieldInfo {
    private String wdis; //商品介绍
    private String propCode; //规格参数
    private String wareQD; //包装清单(仅自营商品)
}
