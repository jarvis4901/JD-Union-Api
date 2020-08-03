package com.yd.jd.api.order.request;

import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.OrderReq;
import com.yd.jd.api.order.response.OrderQueryResponse;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
public class OrderQueryRequest extends AbstractRequest implements JdRequest<OrderQueryResponse> {
    private OrderReq orderReq;

    public OrderQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.order.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("orderReq", this.orderReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<OrderQueryResponse> getResponseClass() {
        return OrderQueryResponse.class;
    }

}
