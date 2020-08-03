package com.yd.jd.api.goods.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.SecKillGoodsReq;
import com.yd.jd.api.goods.response.SeckillGoodsQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 秒杀商品查询
 */
@Data
public class SeckillGoodsQueryRequest extends AbstractRequest implements JdRequest<SeckillGoodsQueryResponse> {
    private SecKillGoodsReq goodsReq;

    public SeckillGoodsQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.goods.seckill.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("goodsReq", this.goodsReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<SeckillGoodsQueryResponse> getResponseClass() {
        return SeckillGoodsQueryResponse.class;
    }

}
