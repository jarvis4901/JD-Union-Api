package com.yd.jd.api.order.request;

import com.yd.jd.api.order.response.OrderRowQueryResponse;
import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.OrderRowReq;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
public class OrderRowQueryRequest extends AbstractRequest implements JdRequest<OrderRowQueryResponse> {
    private OrderRowReq orderReq;

    public OrderRowQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.order.row.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("orderReq", this.orderReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<OrderRowQueryResponse> getResponseClass() {
        return OrderRowQueryResponse.class;
    }

}

