package com.yd.jd.api.goods.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.MaterialGoodsReq;
import com.yd.jd.api.goods.response.MaterialGoodsQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
public class MaterialGoodsQueryRequest extends AbstractRequest implements JdRequest<MaterialGoodsQueryResponse> {
    private MaterialGoodsReq goodsReq;

    public MaterialGoodsQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.goods.material.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("goodsReq", this.goodsReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<MaterialGoodsQueryResponse> getResponseClass() {
        return MaterialGoodsQueryResponse.class;
    }

}
