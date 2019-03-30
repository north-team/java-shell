package com.fit2cloud.java.shell.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final String HTTPS = "https";

    /**
     * 根据url构建HttpClient（区分http和https）
     *
     * @param url 请求地址
     * @return CloseableHttpClient实例
     */
    private static CloseableHttpClient buildHttpClient(String url)  {
        try {
            if (url.startsWith(HTTPS)) {
                // https 增加信任设置
                TrustStrategy trustStrategy = new TrustSelfSignedStrategy();
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStrategy).build();
                HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
                return HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(hostnameVerifier).build();
            } else {
                // http
                return HttpClientBuilder.create().build();
            }
        } catch (Exception e) {
            throw new RuntimeException("HttpClient构建失败", e);
        }
    }

    /**
     * Get
     *
     * @param url    请求地址
     * @param param  请求内容键值对参数
     * @param config 配置项，如果null则使用默认配置
     * @return 响应结果字符串
     */
    public static String get(String url, Map<String, String> param, HttpClientConfig config)  {
        CloseableHttpClient httpClient = buildHttpClient(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setConfig(config.buildRequestConfig());

            Map<String, String> header = config.getHeader();
            for (String key : header.keySet()) {
                httpGet.addHeader(key, header.get(key));
            }
            httpGet.addHeader(HTTP.CONTENT_TYPE, config.getContentType());
            httpGet.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, config.getCharset());
        } catch (Exception e) {
            logger.error("HttpClient_Get查询失败", e);
            throw new RuntimeException("HttpClient_Get查询失败", e);
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                logger.error("HttpClient_Get关闭连接失败", e);
            }
        }
    }

    /**
     * delete
     *
     * @param url    请求地址
     * @param param  请求内容键值对参数
     * @param config 配置项，如果null则使用默认配置
     * @return 响应结果字符串
     */
    public static String delete(String url, Map<String, String> param, HttpClientConfig config)  {
        CloseableHttpClient httpClient = buildHttpClient(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            HttpDelete httpDelete = new HttpDelete(uri);
            httpDelete.setConfig(config.buildRequestConfig());

            Map<String, String> header = config.getHeader();
            for (String key : header.keySet()) {
                httpDelete.addHeader(key, header.get(key));
            }
            httpDelete.addHeader(HTTP.CONTENT_TYPE, config.getContentType());
            httpDelete.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());

            HttpResponse response = httpClient.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, config.getCharset());
        } catch (Exception e) {
            logger.error("HttpClient_Delete查询失败", e);
            throw new RuntimeException("HttpClient_Delete查询失败", e);
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                logger.error("HttpClient_Delete关闭连接失败", e);
            }
        }
    }
    /**
     * delete请求，请求内容必须为JSON格式的字符串
     *
     * @param url    请求地址
     * @param config 配置项，如果null则使用默认配置
     * @param json   JSON格式的字符串
     * @return 响应结果字符串
     */
    public static String deleteJson(String url, String json, HttpClientConfig config)  {
        CloseableHttpClient httpClient = buildHttpClient(url);
        HttpDelete httpDelete = new HttpDelete(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            httpDelete.setConfig(config.buildRequestConfig());

            Map<String, String> header = config.getHeader();
            for (String key : header.keySet()) {
                httpDelete.addHeader(key, header.get(key));
            }
            httpDelete.addHeader(HTTP.CONTENT_TYPE, config.getContentType());
            httpDelete.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());

            EntityBuilder entityBuilder = EntityBuilder.create();
            entityBuilder.setText(json);
            entityBuilder.setContentType(ContentType.APPLICATION_JSON);
            entityBuilder.setContentEncoding(config.getCharset());
            HttpEntity requestEntity = entityBuilder.build();
            httpDelete.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, config.getCharset());
        } catch (Exception e) {
            logger.error("HttpClient_PostJson查询失败", e);
            throw new RuntimeException("HttpClient_PostJson查询失败", e);
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                logger.error("HttpClient_PostJson关闭连接失败", e);
            }
        }
    }
    /**
     * Post请求，请求内容必须为JSON格式的字符串
     *
     * @param url    请求地址
     * @param config 配置项，如果null则使用默认配置
     * @param json   JSON格式的字符串
     * @return 响应结果字符串
     */
    public static String postJson(String url, String json, HttpClientConfig config)  {
        CloseableHttpClient httpClient = buildHttpClient(url);
        HttpPost httpPost = new HttpPost(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            httpPost.setConfig(config.buildRequestConfig());

            Map<String, String> header = config.getHeader();
            for (String key : header.keySet()) {
                httpPost.addHeader(key, header.get(key));
            }
            httpPost.addHeader(HTTP.CONTENT_TYPE, config.getContentType());
            httpPost.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());

            EntityBuilder entityBuilder = EntityBuilder.create();
            entityBuilder.setText(json);
            entityBuilder.setContentType(ContentType.APPLICATION_JSON);
            entityBuilder.setContentEncoding(config.getCharset());
            HttpEntity requestEntity = entityBuilder.build();
            httpPost.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, config.getCharset());
        } catch (Exception e) {
            logger.error("HttpClient_PostJson查询失败", e);
            throw new RuntimeException("HttpClient_PostJson查询失败", e);
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                logger.error("HttpClient_PostJson关闭连接失败", e);
            }
        }
    }

    /**
     * Put请求，请求内容必须为JSON格式的字符串
     *
     * @param url    请求地址
     * @param config 配置项，如果null则使用默认配置
     * @param json   JSON格式的字符串
     * @return 响应结果字符串
     */
    public static String putJson(String url, String json, HttpClientConfig config)  {
        CloseableHttpClient httpClient = buildHttpClient(url);
        HttpPut httpPut = new HttpPut(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            httpPut.setConfig(config.buildRequestConfig());

            Map<String, String> header = config.getHeader();
            for (String key : header.keySet()) {
                httpPut.addHeader(key, header.get(key));
            }
            httpPut.addHeader(HTTP.CONTENT_TYPE, config.getContentType());
            httpPut.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());

            EntityBuilder entityBuilder = EntityBuilder.create();
            entityBuilder.setText(json);
            entityBuilder.setContentType(ContentType.APPLICATION_JSON);
            entityBuilder.setContentEncoding(config.getCharset());
            HttpEntity requestEntity = entityBuilder.build();
            httpPut.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, config.getCharset());
        } catch (Exception e) {
            logger.error("HttpClient_PutJson查询失败", e);
            throw new RuntimeException("HttpClient_PutJson查询失败", e);
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                logger.error("HttpClient_PutJson关闭连接失败", e);
            }
        }
    }

    /**
     * Post请求，请求内容必须为键值对参数
     *
     * @param url    请求地址
     * @param config 配置项，如果null则使用默认配置
     * @param body   请求内容键值对参数
     * @return 响应结果字符串
     */
    public static String post(String url, Map<String, String> body, HttpClientConfig config)  {
        CloseableHttpClient httpClient = buildHttpClient(url);
        HttpPost httpPost = new HttpPost(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            httpPost.setConfig(config.buildRequestConfig());

            Map<String, String> header = config.getHeader();
            for (String key : header.keySet()) {
                httpPost.addHeader(key, header.get(key));
            }
            httpPost.addHeader(HTTP.CONTENT_TYPE, config.getContentType());
            httpPost.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());

            if (body != null && body.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (String key : body.keySet()) {
                    nvps.add(new BasicNameValuePair(key, body.get(key)));
                }
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps, config.getCharset()));
                } catch (Exception e) {
                    logger.error("HttpClient_Post转换编码错误", e);
                    throw new RuntimeException("HttpClient_Post转换编码错误", e);
                }
            }

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, config.getCharset());
        } catch (Exception e) {
            logger.error("HttpClient_Post查询失败", e);
            throw new RuntimeException("HttpClient_Post查询失败", e);
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                logger.error("HttpClient_Post关闭连接失败", e);
            }
        }
    }

    /**
     * Put请求，请求内容必须为键值对参数
     *
     * @param url    请求地址
     * @param config 配置项，如果null则使用默认配置
     * @param body   请求内容键值对参数
     * @return 响应结果字符串
     */
    public static String put(String url, Map<String, String> body, HttpClientConfig config)  {
        CloseableHttpClient httpClient = buildHttpClient(url);
        HttpPut httpPut = new HttpPut(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            httpPut.setConfig(config.buildRequestConfig());

            Map<String, String> header = config.getHeader();
            for (String key : header.keySet()) {
                httpPut.addHeader(key, header.get(key));
            }
            httpPut.addHeader(HTTP.CONTENT_TYPE, config.getContentType());
            httpPut.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());

            if (body != null && body.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (String key : body.keySet()) {
                    nvps.add(new BasicNameValuePair(key, body.get(key)));
                }
                try {
                    httpPut.setEntity(new UrlEncodedFormEntity(nvps, config.getCharset()));
                } catch (Exception e) {
                    logger.error("HttpClient_Put转换编码错误", e);
                    throw new RuntimeException("HttpClient_Put转换编码错误", e);
                }
            }

            HttpResponse response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, config.getCharset());
        } catch (Exception e) {
            logger.error("HttpClient_Put查询失败", e);
            throw new RuntimeException("HttpClient_Put查询失败", e);
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                logger.error("HttpClient_Put关闭连接失败", e);
            }
        }
    }
}
