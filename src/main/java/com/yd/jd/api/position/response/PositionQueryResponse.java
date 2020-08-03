package com.yd.jd.api.position.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.PositionQueryResp;
import com.yd.jd.vo.CommonResult;
import lombok.Data;

@Data
public class PositionQueryResponse extends AbstractResponse {
    private CommonResult<PositionQueryResp> result;
}
