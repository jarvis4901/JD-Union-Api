package com.yd.jd.api.goods.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.StuPriceGoodsReq;
import com.yd.jd.api.goods.response.StuPriceGoodsQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 学生价商品查询
 */
@Data
public class StuPriceGoodsQueryRequest extends AbstractRequest implements JdRequest<StuPriceGoodsQueryResponse> {
    private StuPriceGoodsReq goodsReq;

    public StuPriceGoodsQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.goods.stuprice.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("goodsReq", this.goodsReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<StuPriceGoodsQueryResponse> getResponseClass() {
        return StuPriceGoodsQueryResponse.class;
    }

}
