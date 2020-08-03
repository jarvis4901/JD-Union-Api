package com.yd.jd.api.coupons.gift.request;

import com.yd.jd.api.coupons.gift.response.CouponGiftCreateResponse;
import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.CouponGiftCreateReq;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 礼金创建
 */
@Data
public class CouponGiftCreateRequest extends AbstractRequest implements JdRequest<CouponGiftCreateResponse> {
    private CouponGiftCreateReq couponGiftCreateReq ;

    public CouponGiftCreateRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.coupon.gift.get";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("couponReq", this.couponGiftCreateReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<CouponGiftCreateResponse> getResponseClass() {
        return CouponGiftCreateResponse.class;
    }

}