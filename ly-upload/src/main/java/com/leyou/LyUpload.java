package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: seadragon
 * @Date: 2019-06-13 20:38
 * @Description:
 */

@SpringBootApplication
@EnableDiscoveryClient
public class LyUpload {
    public static void main(String[] args) {
        SpringApplication.run(LyUpload.class, args);
    }
}
