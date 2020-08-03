package com.yd.jd.api.activity.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.ActivityQueryResp;
import com.yd.jd.vo.ListResult;
import lombok.Data;

@Data
public class ActivityQueryResponse extends AbstractResponse {
    private ListResult<ActivityQueryResp> result;
}
