package com.yd.jd.api.category.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.GoodsCategoryQueryResp;
import com.yd.jd.vo.SimpleListResult;
import lombok.Data;

@Data
public class GoodsCategoryQueryResponse extends AbstractResponse {
    private SimpleListResult<GoodsCategoryQueryResp> result;
}
