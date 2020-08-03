package com.yd.jd.common.client;

import com.yd.jd.common.request.JdRequest;
import com.yd.jd.common.response.AbstractResponse;

public interface JdClient {
    <T extends AbstractResponse> AbstractResponse execute(JdRequest<T> var1) throws Exception;
}
