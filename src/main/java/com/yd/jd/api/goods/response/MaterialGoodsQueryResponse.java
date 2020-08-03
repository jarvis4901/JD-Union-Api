package com.yd.jd.api.goods.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.MaterialGoodsQueryResp;
import com.yd.jd.vo.ListResult;
import lombok.Data;

/**
 * 物料商品查询
 */
@Data
public class MaterialGoodsQueryResponse extends AbstractResponse {
    private ListResult<MaterialGoodsQueryResp> result;
}
