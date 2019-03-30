package com.fit2cloud.java.shell.util;

/**
 * @author : zhm
 * @description :
 * @date : 2019/3/8 16:21
 */

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * HttpClient自带的HttpDelete方法是不支持上传body的，所以重写delete方法
 */
public class HttpDelete extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpDelete(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public HttpDelete(final URI uri) {
        super();
        setURI(uri);
    }

    public HttpDelete() {
        super();
    }
}
