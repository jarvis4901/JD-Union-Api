package com.yd.jd.api.statistics.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.GiftCouponQueryResp;
import com.yd.jd.vo.SimpleListResult;
import lombok.Data;

/**
 * 礼金效果数据
 */
@Data
public class GiftCouponQueryResponse extends AbstractResponse {
    private SimpleListResult<GiftCouponQueryResp> result;
}
