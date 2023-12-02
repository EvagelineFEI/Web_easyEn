package com.easyen.easyenglish.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIRestTemplateConfig {
    @Value("${openai.api.key}")
    // 表示从配置文件获取openai.api.key的值
    private String openaiApiKey;

    @Bean
    @Qualifier("openaiRestTemplate")
    public RestTemplate openaiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //RestTemplate这个类是 Spring 框架提供的一个工具类，用于HTTP请求。
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
            //代码RestTemplate. getinterceptors ().add(…)向RestTemplate中添加了一个拦截器。
            //这个拦截器用于在请求执行之前修改请求。
            //在拦截器内部，Authorization被添加到这个RestTemplate发出的每个请求中。
            //它使用Bearer令牌方案，令牌是openaiApiKey。
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
