package com.yd.jd.api.goods.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.JFGoodsReq;
import com.yd.jd.api.goods.response.JingFenGoodsQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


@Data
public class JingFenGoodsQueryRequest extends AbstractRequest implements JdRequest<JingFenGoodsQueryResponse> {
    private JFGoodsReq goodsReq;

    public JingFenGoodsQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.goods.jingfen.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("goodsReq", this.goodsReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<JingFenGoodsQueryResponse> getResponseClass() {
        return JingFenGoodsQueryResponse.class;
    }

}
