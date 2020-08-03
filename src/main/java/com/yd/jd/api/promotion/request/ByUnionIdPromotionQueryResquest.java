package com.yd.jd.api.promotion.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.PromotionCodeReq;
import com.yd.jd.api.promotion.response.ByUnionIdPromotionQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 工具商获取推广链接接口【
 */
@Data
public class ByUnionIdPromotionQueryResquest extends AbstractRequest implements JdRequest<ByUnionIdPromotionQueryResponse> {
    private PromotionCodeReq promotionCodeReq;

    public ByUnionIdPromotionQueryResquest() {
    }

    public String getApiMethod() {
        return "jd.union.open.promotion.byunionid.get";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("promotionCodeReq", this.promotionCodeReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<ByUnionIdPromotionQueryResponse> getResponseClass() {
        return ByUnionIdPromotionQueryResponse.class;
    }

}
