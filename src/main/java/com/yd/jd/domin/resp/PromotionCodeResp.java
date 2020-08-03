package com.yd.jd.domin.resp;

import lombok.Data;

/**
 * 推广链接
 */
@Data
public class PromotionCodeResp {
    private String shortURL; //生成的推广目标链接，以短链接形式，有效期为半年
    private String clickURL; //生成的目标推广链接，长期有效
}
