package com.yd.jd.internal.parser;

import com.yd.jd.common.exception.JdException;
import com.yd.jd.common.response.AbstractResponse;

public class XmlParser implements Parser {
    public XmlParser() {
    }

    public <T extends AbstractResponse> T parse(String formatString, Class<T> responseClass, String apiName) throws JdException {
        return null;
    }
}
