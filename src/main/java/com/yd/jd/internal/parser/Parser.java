package com.yd.jd.internal.parser;

import com.yd.jd.common.exception.JdException;
import com.yd.jd.common.response.AbstractResponse;

public interface Parser {
    <T extends AbstractResponse> T parse(String var1, Class<T> var2, String var3) throws JdException;
}
