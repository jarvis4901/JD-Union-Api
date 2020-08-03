package com.yd.jd.api.promotion.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.PromotionGoodsResp;
import com.yd.jd.vo.CommonResult;

/**
 * 网站/APP获取推广链接接口
 */
public class CommonPromotionQueryResponse extends AbstractResponse {
    private CommonResult<PromotionGoodsResp> result;
}
