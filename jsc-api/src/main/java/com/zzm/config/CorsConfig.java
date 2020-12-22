package com.zzm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author zzm
 * @time 2019/11/15 13:28
 * 跨域
 */
@Configuration
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {

        // 1.添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
//        config.addAllowedOrigin("http://wwww.zhuzhaoman.site");
//        config.addAllowedOrigin("http://localhost:8080");
//        config.addAllowedOrigin("http://127.0.0.1:8080");
//        config.addAllowedOrigin("https://localhost:9528");
//        config.addAllowedOrigin("https://127.0.0.1:9528");

        // 设置是否发送cookie信息
        config.setAllowCredentials(true);

        // 设置允许请求的方式
        config.addAllowedMethod("*");

        // 设置允许的header
        config.addAllowedHeader("*");

        // 2.url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        // 3.返回重新定义好的corsSource
        return new CorsFilter(corsSource);
    }
}
