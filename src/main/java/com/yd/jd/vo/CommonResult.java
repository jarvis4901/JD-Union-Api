package com.yd.jd.vo;

import lombok.Data;

/**
 * 网站/APP获取推广链接接口
 * 社交媒体获取推广链接接口
 * 工具商获取推广链接接口
 * 查询推广位
 * 创建推广位
 * @param <T>
 */
@Data
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;
}
