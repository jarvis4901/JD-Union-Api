package com.yd.jd.api.coupons.gift.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.vo.CouponGiftStopResult;
import lombok.Data;

@Data
public class CouponGiftStopResponse extends AbstractResponse {
    private CouponGiftStopResult couponGiftStopResult;
}
