
package com.yd.jd.domin;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@NoArgsConstructor
public class SkuInfo implements Serializable {
    private double actualCosPrice;
    private double actualFee;
    private double commissionRate;
    private double estimateCosPrice;
    private double estimateFee;
    private double finalRate;
    private long cid1;
    private long frozenSkuNum;
    private String pid;
    private long positionId;
    private double price;
    private long cid2;
    private long siteId;
    private long skuId;
    private String skuName;
    private long skuNum;
    private long skuReturnNum;
    private double subSideRate;
    private double subsidyRate;
    private long cid3;
    private String unionAlias;
    private String unionTag;
    private int unionTrafficGroup;
    private int validCode;
    private String subUnionId;
    private int traceType;
    private int payMonth;
    private long popId;
    private String ext1;
    private long cpActId;
    private int unionRole;
    private String giftCouponKey;
    private double giftCouponOcsAmount;
}
