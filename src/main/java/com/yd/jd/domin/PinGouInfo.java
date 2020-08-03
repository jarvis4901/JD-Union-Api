package com.yd.jd.domin;

import lombok.Data;

/**
 * 拼购信息
 */
@Data
public class PinGouInfo {
    private double pingouPrice;  //拼购价格
    private long pingouTmCount;  //拼购成团所需人数
    private String pingouUrl;  //拼购落地页url https://wq.jd.com/pingou_api/GetAutoTuan?sku_id=35097232463 from=cps
    private long pingouStartTime;  //拼购开始时间(时间戳，毫秒)
    private long pingouEndTime;  //拼购结束时间(时间戳，毫秒)
}
