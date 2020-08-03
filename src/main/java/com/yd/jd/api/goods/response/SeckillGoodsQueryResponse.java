package com.yd.jd.api.goods.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.SecKillGoodsQueryResp;
import com.yd.jd.vo.ListResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeckillGoodsQueryResponse extends AbstractResponse {
    private ListResult<SecKillGoodsQueryResp> result;
}
