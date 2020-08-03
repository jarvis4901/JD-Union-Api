package com.yd.jd.dto;

import lombok.Data;

/**
 * 商品分类 请求入参
 */
@Data
public class CategoryReq {
    private int parentId; //父类目id(一级父类目为0)
    private int grade; //类目级别(类目级别 0，1，2 代表一、二、三级类目)
}
