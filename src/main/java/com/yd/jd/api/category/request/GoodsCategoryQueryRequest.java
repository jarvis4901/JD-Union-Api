package com.yd.jd.api.category.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.CategoryReq;
import com.yd.jd.api.category.response.GoodsCategoryQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
public class GoodsCategoryQueryRequest extends AbstractRequest implements JdRequest<GoodsCategoryQueryResponse> {
    private CategoryReq categoryReq;

    public GoodsCategoryQueryRequest() {
    }

    //test
    public String getApiMethod() {
        return "jd.union.open.category.goods.get";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("req", this.categoryReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<GoodsCategoryQueryResponse> getResponseClass() {
        return GoodsCategoryQueryResponse.class;
    }

}
