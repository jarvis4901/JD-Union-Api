package com.yd.jd.api.coupons.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.CouponResp;
import com.yd.jd.vo.SimpleListResult;
import lombok.Data;

@Data
public class CouponQueryResponse extends AbstractResponse {
    private SimpleListResult<CouponResp> result;
}
