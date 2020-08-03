package com.yd.jd.common.client;

import com.yd.jd.common.exception.JdException;
import com.yd.jd.internal.parser.Parser;
import com.yd.jd.internal.parser.ParserFactory;
import com.yd.jd.common.request.JdRequest;
import com.yd.jd.common.request.JdUploadRequest;
import com.yd.jd.common.response.AbstractResponse;
import com.yd.jd.utils.HttpUtil;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class DefaultJdClient implements JdClient {
    public static final String CHARSET_UTF8 = "UTF-8";
    private static final String JSON_PARAM_KEY = "param_json";
    private static final String OTHER_PARAM_KEY = "other";
    private String serverUrl = "https://router.jd.com/api";
    private String accessToken;
    private int connectTimeout;
    private int readTimeout;
    private String appKey;
    private String fuzz;
    private String appSecret;

    public DefaultJdClient(String accessToken, String appKey, String appSecret) {
        this.connectTimeout = 0;
        this.readTimeout = 0;
        this.accessToken = accessToken;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public DefaultJdClient(String appKey, String appSecret) {
        this.connectTimeout = 0;
        this.readTimeout = 0;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public DefaultJdClient(String accessToken, String appKey, String appSecret, String fuzz) {
        this.connectTimeout = 0;
        this.readTimeout = 0;
        this.accessToken = accessToken;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.fuzz = fuzz;
    }



    public <T extends AbstractResponse> T execute(JdRequest<T> request) throws Exception {
        String url = this.buildUrl(request);
        Map<String, String> params = new HashMap();
        String json = request.getAppJsonParams();
        String rsp = null;
        if (request instanceof JdUploadRequest) {
            rsp = HttpUtil.doPost(url, params, ((JdUploadRequest) request).getFileParams(), this.connectTimeout, this.readTimeout);
        } else {
            rsp = HttpUtil.doPost(url, params, this.connectTimeout, this.readTimeout);
        }
        T resp = this.parse(rsp, request.getResponseClass(), request.getApiMethod());
        StringBuffer sb = new StringBuffer();
//        sb.append(url).append("&").append("360buy_param_json").append("=").append(json);
        sb.append(url);
        resp.setUrl(sb.toString());
        return resp;
    }

    private <T extends AbstractResponse> String buildUrl(JdRequest<T> request) throws Exception {
        Map<String, String> sysParams = request.getSysParams();
        Map<String, String> pmap = new TreeMap();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = formatter.format(date);
        sysParams.put("timestamp", timestamp);
        sysParams.put("v", "1.0");
        sysParams.put("sign_method", "md5");
        sysParams.put("format", "json");
        sysParams.put("method", request.getApiMethod());
        sysParams.put("param_json", request.getAppJsonParams());
        sysParams.put("access_token", this.accessToken);
        sysParams.put("app_key", this.appKey);
        if (this.fuzz != null) {
            sysParams.put("jos_result_fuzz", this.fuzz);
        }

        pmap.putAll(sysParams);
        String sign = this.sign(pmap, this.appSecret);
        sysParams.put("sign", sign);
        StringBuilder sb = new StringBuilder(this.serverUrl);
        sb.append("?");
        sb.append(HttpUtil.buildQuery(sysParams, "UTF-8"));
        return sb.toString();
    }

    private <T extends AbstractResponse> T parse(String rsp, Class<T> responseClass, String apiName) throws JdException {
        Parser parser;
//        if (this.serverUrl.endsWith("json")) {
//            parser = ParserFactory.getJsonParser();
//        } else {
//            parser = ParserFactory.getXmlParser();
//        }
        parser = ParserFactory.getJsonParser();
        return parser.parse(rsp, responseClass, apiName);
    }

    private String sign(Map<String, String> pmap, String appSecret) throws Exception {
        Map<String, String> map = pmap;
        StringBuilder sb = new StringBuilder(appSecret);
        //按照规则拼成字符串
        for (Map.Entry entry : map.entrySet()) {
            String name = (String) entry.getKey();
            String value = (String) entry.getValue();
            //检测参数是否为空
            if (this.areNotEmpty(new String[]{name, value})) {
                sb.append(name).append(value);
            }
        }
        sb.append(appSecret);
        //MD5
        return this.md5(sb.toString());

    }

    public static String md5(String source)
            throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(source.getBytes("utf-8"));
        return byte2hex(bytes);

    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();

    }

    public static boolean areNotEmpty(String[] values) {
        boolean result = true;
        if ((values == null) || (values.length == 0))
            result = false;
        else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;

    }


    public static boolean isEmpty(String value) {
        int strLen;
        if ((value == null) || ((strLen = value.length()) == 0))
            return true;
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;

    }
}