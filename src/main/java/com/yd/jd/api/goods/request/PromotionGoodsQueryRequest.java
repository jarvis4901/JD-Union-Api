package com.yd.jd.api.goods.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.api.goods.response.PromotionGoodsQueryResponse;
import com.yd.jd.utils.JsonUtil;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 根据skuid查询商品信息接口
 */
public class PromotionGoodsQueryRequest extends AbstractRequest implements JdRequest<PromotionGoodsQueryResponse> {
    private String skuIds;

    public PromotionGoodsQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.goods.promotiongoodsinfo.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("skuIds", this.skuIds);
        return JsonUtil.toJson(pmap);
    }

    public Class<PromotionGoodsQueryResponse> getResponseClass() {
        return PromotionGoodsQueryResponse.class;
    }

    @JsonProperty("skuIds")
    public void setSkuIds(String skuIds) {
        this.skuIds = skuIds;
    }

    @JsonProperty("skuIds")
    public String getSkuIds() {
        return this.skuIds;
    }
}