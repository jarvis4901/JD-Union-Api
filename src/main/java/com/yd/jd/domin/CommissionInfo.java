package com.yd.jd.domin;

import lombok.Data;

/**
 * 佣金信息
 */
@Data
public class CommissionInfo {
    private double commission; //佣金
    private double commissionShare; //佣金比例
    private double couponCommission; //券后佣金
    private double plusCommissionShare; //plus佣金比例(即将上线)
}
