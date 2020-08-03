package com.yd.jd.utils;

import com.yd.jd.common.FileItem;
import com.yd.jd.common.exception.JdException;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class HttpUtil {
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final String METHOD_POST = "POST";

    private HttpUtil() {
        throw new UnsupportedOperationException();
    }

    public static String buildQuery(Map<String, String> params, String charset) throws Exception {
        if (params != null && !params.isEmpty()) {
            StringBuilder query = new StringBuilder();
            Set<Map.Entry<String, String>> entries = params.entrySet();
            boolean hasParam = false;
            Iterator var5 = entries.iterator();

            while(var5.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var5.next();
                String name = (String)entry.getKey();
                String value = (String)entry.getValue();
                if (StringUtil.areNotEmpty(new String[]{name, value})) {
                    if (hasParam) {
                        query.append("&");
                    } else {
                        hasParam = true;
                    }

                    query.append(name).append("=").append(URLEncoder.encode(value, charset));
                }
            }

            return query.toString();
        } else {
            return null;
        }
    }

    public static String doPost(String url, Map<String, String> params, int connectTimeout, int readTimeout) throws Exception {
        return doPost(url, params, "UTF-8", connectTimeout, readTimeout);
    }

    public static String doPost(String url, Map<String, String> params, String charset, int connectTimeout, int readTimeout) throws Exception {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        byte[] content = new byte[0];
        if (query != null) {
            content = query.getBytes(charset);
        }

        return doPost(url, ctype, content, connectTimeout, readTimeout);
    }

    public static String doPost(String url, Map<String, String> params, Map<String, FileItem> fileParams, int connectTimeout, int readTimeout) throws Exception {
        return fileParams != null && !fileParams.isEmpty() ? doPost(url, params, fileParams, "UTF-8", connectTimeout, readTimeout) : doPost(url, params, "UTF-8", connectTimeout, readTimeout);
    }

    public static String doPost(String url, Map<String, String> params, Map<String, FileItem> fileParams, String charset, int connectTimeout, int readTimeout) throws IOException, JdException {
        String boundary = System.currentTimeMillis() + "";
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;

        try {
            try {
                String ctype = "multipart/form-data;charset=" + charset + ";boundary=" + boundary;
                conn = getConnection(new URL(url), "POST", ctype);
                conn.setRequestProperty("uuid", UUID.randomUUID().toString());
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException var22) {
                throw new JdException(var22);
            }

            try {
                out = conn.getOutputStream();
                byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(charset);
                Set<Map.Entry<String, String>> textEntrySet = params.entrySet();
                Iterator var12 = textEntrySet.iterator();

                while(var12.hasNext()) {
                    Map.Entry<String, String> textEntry = (Map.Entry)var12.next();
                    byte[] textBytes = getTextEntry((String)textEntry.getKey(), (String)textEntry.getValue(), charset);
                    out.write(entryBoundaryBytes);
                    out.write(textBytes);
                }

                Set<Map.Entry<String, FileItem>> fileEntrySet = fileParams.entrySet();
                Iterator var27 = fileEntrySet.iterator();

                while(var27.hasNext()) {
                    Map.Entry<String, FileItem> fileEntry = (Map.Entry)var27.next();
                    FileItem fileItem = (FileItem)fileEntry.getValue();
                    byte[] fileBytes = getFileEntry((String)fileEntry.getKey(), fileItem.getFileName(), fileItem.getMimeType(), charset);
                    out.write(entryBoundaryBytes);
                    out.write(fileBytes);
                    byte[] content = fileItem.getContent();
                    content = content == null ? new byte[0] : content;
                    out.write(content);
                }

                byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes(charset);
                out.write(endBoundaryBytes);
                rsp = getResponseAsString(conn);
            } catch (IOException var23) {
                throw new JdException(var23);
            }
        } finally {
            if (out != null) {
                out.close();
            }

            if (conn != null) {
                conn.disconnect();
            }

        }

        return rsp;
    }

    public static String doPost(String url, String ctype, byte[] content, int connectTimeout, int readTimeout) throws IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;

        try {
            conn = getConnection(new URL(url), "POST", ctype);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.setRequestProperty("uuid", UUID.randomUUID().toString());
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
        } finally {
            if (out != null) {
                out.close();
            }

            if (conn != null) {
                conn.disconnect();
            }

        }

        return rsp;
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype) throws IOException {
        HttpURLConnection conn = null;
        if ("https".equals(url.getProtocol())) {
            SSLContext ctx = null;

            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new DefaultTrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            } catch (Exception var6) {
                throw new IOException(var6);
            }

            HttpsURLConnection connHttps = (HttpsURLConnection)url.openConnection();
            connHttps.setSSLSocketFactory(ctx.getSocketFactory());
            connHttps.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            conn = connHttps;
        } else {
            conn = (HttpURLConnection)url.openConnection();
        }

        ((HttpURLConnection)conn).setRequestMethod(method);
        ((HttpURLConnection)conn).setDoInput(true);
        ((HttpURLConnection)conn).setDoOutput(true);
        ((HttpURLConnection)conn).setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        ((HttpURLConnection)conn).setRequestProperty("User-Agent", "360buy-sdk-java");
        ((HttpURLConnection)conn).setRequestProperty("Content-Type", ctype);
        return (HttpURLConnection)conn;
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtil.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();
            char[] chars = new char[256];
            boolean var5 = false;

            int count;
            while((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            String var6 = writer.toString();
            return var6;
        } finally {
            if (stream != null) {
                stream.close();
            }

        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = "UTF-8";
        if (!StringUtil.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            String[] var3 = params;
            int var4 = params.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String param = var3[var5];
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2 && !StringUtil.isEmpty(pair[1])) {
                        charset = pair[1].trim();
                    }
                    break;
                }
            }
        }

        return charset;
    }

    private static byte[] getTextEntry(String fieldName, String fieldValue, String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\"\r\nContent-Type:text/plain\r\n\r\n");
        entry.append(fieldValue);
        return entry.toString().getBytes(charset);
    }

    private static byte[] getFileEntry(String fieldName, String fileName, String mimeType, String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\";filename=\"");
        entry.append(fileName);
        entry.append("\"\r\nContent-Type:");
        entry.append(mimeType);
        entry.append("\r\n\r\n");
        return entry.toString().getBytes(charset);
    }
}