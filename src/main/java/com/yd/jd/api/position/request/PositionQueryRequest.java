package com.yd.jd.api.position.request;

import com.yd.jd.api.position.response.PositionQueryResponse;
import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.PositionReq;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
public class PositionQueryRequest extends AbstractRequest implements JdRequest<PositionQueryResponse> {
    private PositionReq positionReq;

    public PositionQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.position.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("positionReq", this.positionReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<PositionQueryResponse> getResponseClass() {
        return PositionQueryResponse.class;
    }

}

