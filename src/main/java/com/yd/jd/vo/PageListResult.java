package com.yd.jd.vo;

import lombok.Data;

import java.util.List;

/**
 * 订单查询接口
 * 订单行查询接口
 * @param <T>
 */
@Data
public class PageListResult<T> {
    private int code;
    private String message;
    private List<T> data;
    private Boolean hasMore;
}
