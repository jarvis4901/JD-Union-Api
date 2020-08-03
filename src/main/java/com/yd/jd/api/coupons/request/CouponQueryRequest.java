package com.yd.jd.api.coupons.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.api.coupons.response.CouponQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class CouponQueryRequest extends AbstractRequest implements JdRequest<CouponQueryResponse> {
    private List<String> couponUrls ;

    public CouponQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.coupon.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("couponUrls", this.couponUrls);
        return JsonUtil.toJson(pmap);
    }

    public Class<CouponQueryResponse> getResponseClass() {
        return CouponQueryResponse.class;
    }

}
