package com.yd.jd.api.goods.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.GoodsReq;
import com.yd.jd.api.goods.response.GoodsQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 根据关键字查询商品
 */
@Data
public class GoodsQueryRequest  extends AbstractRequest implements JdRequest<GoodsQueryResponse> {
    private GoodsReq goodsReqDTO;

    public GoodsQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.goods.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("goodsReqDTO", this.goodsReqDTO);
        return JsonUtil.toJson(pmap);
    }

    public Class<GoodsQueryResponse> getResponseClass() {
        return GoodsQueryResponse.class;
    }
}
