package com.yd.jd.api.goods.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.PromotionGoodsResp;
import com.yd.jd.vo.SimpleListResult;
import lombok.Data;

/**
 * 根据skuid查询商品返回结果
 */
@Data
public class PromotionGoodsQueryResponse extends AbstractResponse {
    private SimpleListResult<PromotionGoodsResp> result;
}