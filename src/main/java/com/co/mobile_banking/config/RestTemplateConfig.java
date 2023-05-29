package com.co.mobile_banking.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class RestTemplateConfig {
    /**
     * - RestTemplate : used to simplifies ( សម្រួល ) the process of making http request to external APIs or
     *   service . "it mean it help to simplify the request of http such as postForEntity to request post mapping
     *   to something like oneSignal or firebase"
     * - builder.build() : is uses to build that RestTemplate
     */
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
