package com.yd.jd.domin.resp;

import lombok.Data;

/**
 * 商品分类
 */
@Data
public class GoodsCategoryQueryResp {
    private int id; //类目Id
    private String name; //类目名称
    private int grade; //类目级别(类目级别 0，1，2 代表一、二、三级类目)
    private int parentId; //父类目Id
}
