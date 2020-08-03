package com.yd.jd.api.activity.request;

import com.yd.jd.api.activity.response.ActivityQueryResponse;
import com.yd.jd.common.request.AbstractRequest;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.dto.ActivityReq;
import com.yd.jd.utils.JsonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Data
public class ActivityQueryRequest extends AbstractRequest implements JdRequest<ActivityQueryResponse> {
    private ActivityReq activityReq;

    public ActivityQueryRequest() {
    }

    public String getApiMethod() {
        return "jd.union.open.activity.query";
    }

    public String getAppJsonParams() throws IOException {
        Map<String, Object> pmap = new TreeMap();
        pmap.put("activityReq", this.activityReq);
        return JsonUtil.toJson(pmap);
    }

    public Class<ActivityQueryResponse> getResponseClass() {
        return ActivityQueryResponse.class;
    }

}

