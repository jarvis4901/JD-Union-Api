package com.yd.jd.api.statistics.request;

import com.yd.jd.api.statistics.response.GiftCouponQueryResponse;
import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.GiftCouponEffectDataReq;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 礼金效果数据
 */
@Data
public class GiftCouponQueryRequest extends AbstractRequest implements JdRequest<GiftCouponQueryResponse> {
    private GiftCouponEffectDataReq giftCouponEffectDataReq;

    public GiftCouponQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.statistics.giftcoupon.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("effectDataReq", this.giftCouponEffectDataReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<GiftCouponQueryResponse> getResponseClass() {
        return GiftCouponQueryResponse.class;
    }

}
