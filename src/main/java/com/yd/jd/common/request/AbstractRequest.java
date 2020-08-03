package com.yd.jd.common.request;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRequest {
    private final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
    protected String timestamp;
    protected String version;
    protected String method;
    protected String sign;

    public AbstractRequest() {
        this.timestamp = this.sdf.format(new Date());
        this.version = "1.0";
    }

    public Map<String, String> getSysParams() {
        Map<String, String> sysParams = new HashMap();
        sysParams.put("method", this.method);
        sysParams.put("timestamp", this.timestamp);
        sysParams.put("v", this.version);
        return sysParams;
    }

    protected String getMethod() {
        return this.method;
    }

    protected void setMethod(String method) {
        this.method = method;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    protected String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    protected String getSign() {
        return this.sign;
    }

    protected void setSign(String sign) {
        this.sign = sign;
    }

    public String getOtherParams() throws IOException {
        return null;
    }
}