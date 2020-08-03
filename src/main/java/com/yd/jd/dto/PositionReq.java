package com.yd.jd.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询推广位 请求入参
 */
@Data
@NoArgsConstructor
public class PositionReq {
    private long unionId;//需要查询的目标联盟id
    private String key;//目标联盟ID对应的授权key，在联盟推广管理页领取
    /**
     * 联盟推广位类型
     * 1：官网和pid接口创建的推广位（含2020.4.28日前【批量创建推广位】接口创建的推广位），
     * 2:cpc推广位，
     * 3:私域会员推广位（2020.4.28之后【批量创建推广位】接口创建的推广位）
     */
    private int unionType;
    private int pageIndex;
    private int pageSize;
}
