package com.yd.jd.api.order.response;


import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.OrderRowResp;
import com.yd.jd.vo.PageListResult;
import lombok.Data;

@Data
public class OrderRowQueryResponse extends AbstractResponse {
    private PageListResult<OrderRowResp> result;
}
