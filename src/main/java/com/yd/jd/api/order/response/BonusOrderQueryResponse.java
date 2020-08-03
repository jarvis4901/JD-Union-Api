package com.yd.jd.api.order.response;

import com.yd.jd.common.response.AbstractResponse;

import com.yd.jd.domin.resp.BonusOrderResp;
import com.yd.jd.vo.SimpleListResult;
import lombok.Data;


/**
 * 奖励订单接口
 */
@Data
public class BonusOrderQueryResponse extends AbstractResponse {
    private SimpleListResult<BonusOrderResp> result;
}
