package com.yd.jd.api.goods.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.StuPriceGoodsQueryResp;
import com.yd.jd.vo.ListResult;
import lombok.Data;

@Data
public class StuPriceGoodsQueryResponse extends AbstractResponse {
    private ListResult<StuPriceGoodsQueryResp> result;
}
