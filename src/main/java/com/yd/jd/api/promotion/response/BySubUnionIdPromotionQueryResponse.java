package com.yd.jd.api.promotion.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.PromotionGoodsResp;
import com.yd.jd.vo.CommonResult;
import lombok.Data;

/**
 * 社交媒体获取推广链接接口
 */
@Data
public class BySubUnionIdPromotionQueryResponse extends AbstractResponse {
    private CommonResult<PromotionGoodsResp> result;
}
