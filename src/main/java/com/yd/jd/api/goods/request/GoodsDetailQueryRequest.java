package com.yd.jd.api.goods.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.BigFieldGoodsReq;
import com.yd.jd.api.goods.response.GoodsDetailQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
public class GoodsDetailQueryRequest extends AbstractRequest implements JdRequest<GoodsDetailQueryResponse> {
    private BigFieldGoodsReq goodsReq;

    public GoodsDetailQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.goods.bigfield.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("goodsReq", this.goodsReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<GoodsDetailQueryResponse> getResponseClass() {
        return GoodsDetailQueryResponse.class;
    }

}