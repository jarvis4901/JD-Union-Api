package com.yd.jd.vo;

import lombok.Data;

import java.util.List;

/**
 * 京粉精选商品查询接口
 * 秒杀商品查询接口
 * 学生价商品查询接口
 * 物料商品查询
 * 活动查询接口
 *
 */
@Data
public class ListResult<T> {
    private int code;
    private String message;
    private List<T> data;
    private long totalCount;
}
