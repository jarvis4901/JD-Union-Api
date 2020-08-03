package com.yd.jd.api.promotion.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.PromotionCodeReq;
import com.yd.jd.api.promotion.response.BySubUnionIdPromotionQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 社交媒体获取推广链接接口
 */
@Data
public class BySubUnionIdPromotionQueryRequest extends AbstractRequest implements JdRequest<BySubUnionIdPromotionQueryResponse> {
    private PromotionCodeReq promotionCodeReq;

    public BySubUnionIdPromotionQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.promotion.bysubunionid.get";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("promotionCodeReq", this.promotionCodeReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<BySubUnionIdPromotionQueryResponse> getResponseClass() {
        return BySubUnionIdPromotionQueryResponse.class;
    }

}
