package com.yd.jd.api.coupons.gift.request;

import com.yd.jd.api.coupons.gift.response.CouponGiftStopResponse;
import com.yd.jd.api.coupons.response.CouponQueryResponse;
import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class CouponGiftStopRequest extends AbstractRequest implements JdRequest<CouponGiftStopResponse> {
    private String giftCouponKey ;

    public CouponGiftStopRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.coupon.gift.stop";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("couponReq", this.giftCouponKey);
        return JsonUtil.toJson(pmap);
    }

    public Class<CouponGiftStopResponse> getResponseClass() {
        return CouponGiftStopResponse.class;
    }

}
