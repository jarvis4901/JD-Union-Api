package com.yd.jd.api.goods.response;

import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.domin.resp.JFGoodsResp;
import com.yd.jd.vo.ListResult;
import lombok.Data;

/**
 * 京粉商品查询resposne
 */
@Data
public class JingFenGoodsQueryResponse extends AbstractResponse {
    private ListResult<JFGoodsResp> result;
}
