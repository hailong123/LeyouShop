package com.leyou.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Auther: seadragon
 * @Date: 2019-06-09 21:50
 * @Description:
 */

@Configuration
public class GloablCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        //1):允许的域 不要写*, 否则cookie就无法使用了
        configuration.addAllowedOrigin("http://manage.leyou.com");
        //2):是否发送Cookie信息
        configuration.setAllowCredentials(true);
        //3):允许的请求方法
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        //4):允许的头信息
        configuration.addAllowedHeader("*");
        //5):设置有效时长
        configuration.setMaxAge(3600L);

        //2.添加映射路径 我们拦截一切请求
        UrlBasedCorsConfigurationSource configurationSource  = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",configuration);

        //3.返回新的CorsFilter
        return new CorsFilter(configurationSource);
    }

}
