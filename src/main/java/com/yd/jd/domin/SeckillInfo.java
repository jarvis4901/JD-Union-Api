package com.yd.jd.domin;

import lombok.Data;

/**
 * 秒杀信息
 */
@Data
public class SeckillInfo {
    private double seckillOriPrice; //秒杀价原价
    private double seckillPrice; //秒杀价
    private long seckillStartTime; //秒杀开始时间(时间戳，毫秒)
    private long seckillEndTime; //秒杀结束时间(时间戳，毫秒)
}
