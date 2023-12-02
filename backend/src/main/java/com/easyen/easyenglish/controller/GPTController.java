package com.easyen.easyenglish.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.easyen.easyenglish.dto.gptRequest;
import com.easyen.easyenglish.dto.gptResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GPTController {
    @Qualifier("openaiRestTemplate")
    // @Qualifier 注解，我们可以消除需要注入哪个 bean 的问题;当类的实现有多个bean的时候
    @Autowired
    private RestTemplate restTemplate;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiUrl;

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "prompt", defaultValue = "hi") String prompt)
    {
        // create a request
        gptRequest request = new gptRequest(model, prompt);
        // call the API
        gptResponse response = restTemplate.postForObject(apiUrl, request, gptResponse.class);
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }

    public static void main(String[] args) {
        SpringApplication.run(GPTController.class);
    }

}
