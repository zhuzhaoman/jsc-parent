package com.zzm.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zhuzhaoman
 * @date 2020/8/20 0020 14:34
 * @description 获取netty配置信息
 */
@Component
@ConfigurationProperties(prefix = "netty")
@PropertySource("classpath:netty.properties")
public class NettyResource {

    private String host;
    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
