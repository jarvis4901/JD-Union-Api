package com.yd.jd.common.request;

import com.yd.jd.common.response.AbstractResponse;

import java.io.IOException;
import java.util.Map;

public interface JdRequest<T extends AbstractResponse> {
    String getApiMethod();

    Map<String, String> getSysParams();

    String getAppJsonParams() throws IOException;

    String getOtherParams() throws IOException;

    Class<T> getResponseClass();
}
