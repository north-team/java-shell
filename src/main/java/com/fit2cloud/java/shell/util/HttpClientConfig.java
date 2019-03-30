package com.fit2cloud.java.shell.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;

import java.util.HashMap;
import java.util.Map;

public class HttpClientConfig {
    private String charset = "UTF-8";
    private String contentType = "application/json";
    private Map<String, String> header = new HashMap();
    private int connectTimeout = 5000;
    private int connectionRequestTimeout = 5000;
    private int cocketTimeout = 60000;

    public HttpClientConfig() {
    }

    public RequestConfig buildRequestConfig() {
        Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(this.connectTimeout);
        builder.setConnectionRequestTimeout(this.connectionRequestTimeout);
        builder.setSocketTimeout(this.cocketTimeout);
        return builder.build();
    }

    public String getCharset() {
        return this.charset;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Map<String, String> getHeader() {
        return this.header;
    }

    public void addHeader(String key, String value) {
        this.header.put(key, value);
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getConnectionRequestTimeout() {
        return this.connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getCocketTimeout() {
        return this.cocketTimeout;
    }

    public void setCocketTimeout(int cocketTimeout) {
        this.cocketTimeout = cocketTimeout;
    }
}

