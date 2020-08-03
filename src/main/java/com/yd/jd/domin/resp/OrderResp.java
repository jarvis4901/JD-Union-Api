package com.yd.jd.domin.resp;

import com.yd.jd.domin.SkuInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderResp implements Serializable {
    private long finishTime;
    private int orderEmt;
    private long orderId;
    private long orderTime;
    private long parentId;
    private String payMonth;
    private int plus;
    private long popId;
    private SkuInfo[] skuList;
    private long unionId;
    private String ext1;
    private int validCode;
}

