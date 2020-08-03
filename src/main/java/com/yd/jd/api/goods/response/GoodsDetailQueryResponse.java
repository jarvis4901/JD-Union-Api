package com.yd.jd.api.goods.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.GoodsDetailQueryResp;
import com.yd.jd.vo.SimpleListResult;
import lombok.Data;

@Data
public class GoodsDetailQueryResponse extends AbstractResponse {
    private SimpleListResult<GoodsDetailQueryResp> result;
}
