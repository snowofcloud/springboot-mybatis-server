package com.q8888.springboot.mybatis.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application.yml"})
@Data
public class OutSideUrlConfig {
    /**
     * 统一API网关地址
     */
    @Value("${:app.outside.apiUrl}")
    private String apiUrl;
    /**
     * 一企一档的token
     */
    @Value("${:app.outside.accessToken}")
    private String accessToken;
    /**
     * DSE配置信息
     */
    @Value("${:app.outside.dseUrl}")
    private String dseUrl;
    /**
     * 视频配置
     */
    @Value("${:app.outside.videoUrl}")
    private String videoUrl;
    /**
     * 视频配置
     */
    @Value("${:app.outside.videoListUrl}")
    private String videoListUrl;
    /**
     * 视频配置-角色
     */
    @Value("${:app.outside.videoListByRoleUrl}")
    private String videoListByRoleUrl;
    /**
     * 上传文件配置
     */
    @Value("${:app.outside.uploadUrl}")
    private String uploadUrl;
    /**
     * 上传文件配置-B64
     */
    @Value("${:app.outside.uploadB64Url}")
    private String uploadB64Url;
    /**
     * 上传文件配置-桶
     */
    @Value("${:app.outside.bucketName}")
    private String bucketName;
}
