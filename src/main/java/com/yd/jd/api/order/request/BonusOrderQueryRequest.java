package com.yd.jd.api.order.request;

import com.yd.jd.api.order.response.BonusOrderQueryResponse;
import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.BonusOrderReq;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 奖励订单接口
 */
@Data
public class BonusOrderQueryRequest extends AbstractRequest implements JdRequest<BonusOrderQueryResponse> {
    private BonusOrderReq orderReq;

    public BonusOrderQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.order.bonus.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("orderReq", this.orderReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<BonusOrderQueryResponse> getResponseClass() {
        return BonusOrderQueryResponse.class;
    }

}
