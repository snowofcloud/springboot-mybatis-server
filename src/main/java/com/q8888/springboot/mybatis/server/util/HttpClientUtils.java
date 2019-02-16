package com.q8888.springboot.mybatis.server.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @auther xuxq
 * @date 2018/12/6 16:44
 */
public class HttpClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
    //传入接口地址
    public static String getResult(String remoteUrl){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String s=null;
        try {
            HttpPost httpPost = new HttpPost(remoteUrl);
            HttpResponse execute = httpClient.execute(httpPost);
            HttpEntity entity = execute.getEntity();
            if (entity != null){
                s = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            LOGGER.error("请求索思接口失败",e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                LOGGER.warn("关闭索思接口httpClient失败",e);
            }
        }
        return s;

    }
}
