package com.yd.jd.api.position.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.PositionCreateResp;
import com.yd.jd.vo.CommonResult;
import lombok.Data;

@Data
public class PositionCreateResponse extends AbstractResponse {
    private CommonResult<PositionCreateResp> result;
}
