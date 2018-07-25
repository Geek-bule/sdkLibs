package com.herman.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by mars on 2017/8/4.
 */
public class HttpClientUitl {
    /**
     * Created on   2015年7月22日
     * Discription: [模拟post请求，进行远程访问并获取返回值]
     * @param body
     * @param header
     * @param URL
     * @return String
     * @author:     suliang
     * @update:     2015年7月22日 下午8:44:46
     */
    public static String javahttpPost(Map<String,Object> body,Map<String,String> header, String URL) {
        //HttpClient client = new DefaultHttpClient();
        HttpClient client = HttpClientBuilder.create().build();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                //.setCookieSpec(CookieSpecs.DEFAULT).setExpectContinueEnabled(true)
                //.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                //.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setSocketTimeout(5000).setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        try {
            String getStr = "";
            /** post方式
             client.getHttpConnectionManager().getParams().setConnectionTimeout(4000);// 设置连接超时时间为2秒（连接初始化时间）
             PostMethod postMethod = new PostMethod(URL);
             */
            HttpPost httpost = new HttpPost(URL);
            httpost.setConfig(defaultRequestConfig);
            httpost.setHeader("Content-type", "application/json; charset=utf-8");
            if (header != null) {
                Iterator<String> iter = header.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    String value = header.get(key);
                    httpost.setHeader(key,value);
                }
            }

            // 参数设置
            // 遍历map
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if (body != null) {
                Iterator<String> iter = body.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Object value = body.get(key);
                    nvps.add(new BasicNameValuePair(key, value.toString()));
                    //postMethod.setParameter(key, value + "");
                }
                // 执行postMethod
                //client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
                httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

                // 执行并返回状
                HttpResponse response = client.execute(httpost);

                //int status = client.executeMethod(postMethod);
                int status = response.getStatusLine().getStatusCode();
                if (status == HttpStatus.SC_OK) {
                    //getStr = postMethod.getResponseBodyAsString();
                    getStr = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                }
            }
            //postMethod.releaseConnection();
            httpost.releaseConnection();
            return getStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            //client.getHttpConnectionManager().closeIdleConnections(1);
            //client.getConnectionManager().closeExpiredConnections();
            HttpClientUtils.closeQuietly(client);
        }
    }

    /**
     * Created on   2015年7月22日
     * Discription: [模拟get请求]
     * @param URL
     * @return String
     * @author:     suliang
     * @update:     2015年7月22日 下午8:44:14
     */
    public static String javahttpGet(String URL) {
        //HttpClient client = new DefaultHttpClient();
        HttpClient client = HttpClientBuilder.create().build();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                //.setCookieSpec(CookieSpecs.DEFAULT).setExpectContinueEnabled(true)
                //.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                //.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setSocketTimeout(5000).setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        try {
			/*client.getHttpConnectionManager().getParams()
					.setConnectionTimeout(5000);// 设置连接超时时间为2秒（连接初始化时间）
			GetMethod getMethod = new GetMethod(URL + "&t=" + System.currentTimeMillis());*/
            HttpGet httpget = new HttpGet(URL+ "&t=" + System.currentTimeMillis());
            httpget.setConfig(defaultRequestConfig);
            httpget.addHeader("Content-type", "text/html; charset=utf-8");

            //client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");

            // 执行并返回状
            //int status = client.executeMethod(getMethod);
            HttpResponse response = client.execute(httpget);
            String getStr = "";
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                //getStr = getMethod.getResponseBodyAsString();
                getStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                //System.out.println(getMethod.getResponseBodyAsString());
                System.out.println(EntityUtils.toString(response.getEntity(), HTTP.UTF_8));
            }
            httpget.releaseConnection();
            return getStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            //client.getHttpConnectionManager().closeIdleConnections(0);
            //client.getConnectionManager().closeExpiredConnections();
            HttpClientUtils.closeQuietly(client);
        }
    }

    /**
     * Created on   2015年8月25日
     * Discription: [通过url抓取html页面内容]
     * @param URL
     * @return String
     * @author:     suliang
     * @update:     2015年8月25日 上午10:48:44
     */
    public static String getHtmlContentByUrl(String URL) {
        //HttpClient client = new HttpClient();
        //HttpClient client = new DefaultHttpClient();
        HttpClient client = HttpClientBuilder.create().build();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                //.setCookieSpec(CookieSpecs.DEFAULT).setExpectContinueEnabled(true)
                //.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                //.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setSocketTimeout(5000).setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        try {
			/*client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);// 设置连接超时时间为2秒（连接初始化时间）
			GetMethod getMethod = new GetMethod(URL);
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");*/
            HttpGet httpget = new HttpGet(URL);
            httpget.setConfig(defaultRequestConfig);

            HttpResponse response = client.execute(httpget);

            // 执行并返回状态
            //int status = client.executeMethod(getMethod);
            String getStr = "";
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                //getStr = getMethod.getResponseBodyAsString();
                getStr = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
            } else {
                //System.out.println(getMethod.getResponseBodyAsString());
            }
            httpget.releaseConnection();
            return getStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            //client.getHttpConnectionManager().closeIdleConnections(0);
            //client.getConnectionManager().closeExpiredConnections();
            HttpClientUtils.closeQuietly(client);
        }
    }
}
