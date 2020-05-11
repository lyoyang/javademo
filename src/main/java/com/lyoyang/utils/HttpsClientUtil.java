package com.lyoyang.utils;

import com.ipaynow.common.config.SystemConfig;
import com.ipaynow.common.exception.CommonException;
import com.ipaynow.common.exception.CommonExceptionEnum;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author: yangbing
 * @Date: 2020/4/7 16:04
 * @Description:
 */
public class HttpsClientUtil {


    public static String sendPost(String url, String content, Map<String, String> header) throws IOException {
        try (CloseableHttpClient closeableHttpClient = createTLSClient()) {
            return sendPost(url, content, closeableHttpClient, "application/x-www-form-urlencoded", header);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static CloseableHttpClient createTLSClient() throws CommonException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslCsf =
                    new SSLConnectionSocketFactory(sslContext, new String[] {"TLSv1.2"}, null, new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    });

            return HttpClients.custom().setSSLSocketFactory(sslCsf).build();
        } catch (Exception e) {
            throw new CommonException(e, CommonExceptionEnum.HTTP_EXCEPTION);
        }
    }


    private static String sendPost(String url, String content, CloseableHttpClient closeableHttpClient,
                                   String contentType, Map<String, String> headers) throws CommonException, IOException {

        CloseableHttpResponse closeableHttpResponse;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig =
                    RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
            httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);
            httpPost.setConfig(requestConfig);

            // 发送请求
            httpPost.setEntity(new StringEntity(content, SystemConfig.UTF_8));
            httpPost.setHeader("Content-Type", contentType);

            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            closeableHttpResponse = closeableHttpClient.execute(httpPost);

            // 处理响应
            return EntityUtils.toString(closeableHttpResponse.getEntity(), SystemConfig.UTF_8);
        } catch (Exception e) {
            throw e;
        }
    }


    public static String doGet(String url, String charset, Map<String, String> header) throws IOException {
        return doGet(url, charset, 10000, 30000, header);
    }


    public static String doGet(String url, String charset, int requestTimeOut, int reponseTimeOut, Map<String, String> header) throws IOException {
        HttpMethod httpMethod = null;
        BufferedReader br = null;
        try {
            HttpClient client = new HttpClient();
            httpMethod = new GetMethod(url);
            httpMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpMethod.addRequestHeader(entry.getKey(), entry.getValue());
                }
            }
            client.getHttpConnectionManager().getParams().setConnectionTimeout(requestTimeOut);
            client.getHttpConnectionManager().getParams().setSoTimeout(reponseTimeOut);

            client.executeMethod(httpMethod);

            InputStream responseBody = httpMethod.getResponseBodyAsStream();
            br = new BufferedReader(new InputStreamReader(responseBody, charset));

            StringBuffer stringBuffer = new StringBuffer();
            String inputLine = null;
            while ((inputLine = br.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            return stringBuffer.toString().trim();
        } finally {
            if (httpMethod != null) {
                httpMethod.releaseConnection();
            }
            if (br != null) {
                br.close();
            }
        }
    }




}
