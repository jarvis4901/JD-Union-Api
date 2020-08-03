package com.yd.jd.api.goods.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.vo.GoodsQueryResult;
import lombok.Data;

/**
 * 根据关键字查询商品resposne
 */
@Data
public class GoodsQueryResponse extends AbstractResponse {
    private GoodsQueryResult result;
}
