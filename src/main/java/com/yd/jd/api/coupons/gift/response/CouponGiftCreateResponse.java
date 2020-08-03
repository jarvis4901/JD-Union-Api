package com.yd.jd.api.coupons.gift.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.CouponGiftCreateResp;
import com.yd.jd.vo.CommonResult;
import lombok.Data;

@Data
public class CouponGiftCreateResponse extends AbstractResponse {
    private CommonResult<CouponGiftCreateResp> result;
}
