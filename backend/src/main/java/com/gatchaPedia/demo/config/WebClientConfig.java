package com.gatchaPedia.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // 비동기식으로 파이썬 서버와 통신하기위해 사용한다고 함
    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
}
