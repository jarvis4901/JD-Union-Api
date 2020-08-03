package com.yd.jd.api.position.request;

import com.yd.jd.api.position.response.PositionCreateResponse;
import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.PositionReq;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
public class PositionCreateRequest extends AbstractRequest implements JdRequest<PositionCreateResponse> {
    private PositionReq positionReq;

    public PositionCreateRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.position.create";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("positionReq", this.positionReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<PositionCreateResponse> getResponseClass() {
        return PositionCreateResponse.class;
    }

}
