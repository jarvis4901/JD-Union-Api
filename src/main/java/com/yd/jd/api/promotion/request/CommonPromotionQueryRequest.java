package com.yd.jd.api.promotion.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.PromotionCodeReq;
import com.yd.jd.api.promotion.response.CommonPromotionQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 网站/APP获取推广链接接口
 */
@Data
public class CommonPromotionQueryRequest extends AbstractRequest implements JdRequest<CommonPromotionQueryResponse> {
    private PromotionCodeReq promotionCodeReq;

    public CommonPromotionQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.promotion.common.get";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("promotionCodeReq", this.promotionCodeReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<CommonPromotionQueryResponse> getResponseClass() {
        return CommonPromotionQueryResponse.class;
    }

}
