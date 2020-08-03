package com.yd.jd.common.exception;

public class JdException extends Exception {
    private static final long serialVersionUID = -7035498848577048685L;
    private String errCode;
    private String errMsg;

    public JdException() {
    }

    public JdException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdException(String message) {
        super(message);
    }

    public JdException(Throwable cause) {
        super(cause);
    }

    public JdException(String errCode, String errMsg) {
        super(errCode + ": " + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
