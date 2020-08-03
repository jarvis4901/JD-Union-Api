package com.yd.jd.api.order.response;

import com.yd.jd.domin.resp.OrderResp;
import com.yd.jd.vo.PageListResult;
import com.yd.jd.common.response.AbstractResponse;
import lombok.Data;

@Data
public class OrderQueryResponse extends AbstractResponse {
    private PageListResult<OrderResp> result;
}
