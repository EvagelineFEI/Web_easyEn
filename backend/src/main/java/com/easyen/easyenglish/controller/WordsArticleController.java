package com.easyen.easyenglish.controller;

import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;


@RestController
@Slf4j
public class WordsArticleController {
    @PostMapping("/article")
    public String get_article()  throws Exception {
/*        RestTemplate restTemplate = new RestTemplate();
        String url = "http://47.108.188.196:8000/article";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String result = response.getBody();
        System.out.println(result);*/
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/article");
        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        String jsonResponds = EntityUtils.toString(httpEntity);
        System.out.println("Response body: " + jsonResponds);
        return jsonResponds;
    }

    @PostMapping("/user_practice") //上传练习单词
    public String user_practice(@RequestParam(value = "user_id") String user_id,
                                @RequestParam(value = "word_id") String word_id) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        log.info("http://47.108.188.196:8000/user_practice?"+"user_id="+user_id+"&word_id="+word_id);
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/user_practice?"+"user_id="+user_id+"&word_id="+word_id);
        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        String jsonResponds = EntityUtils.toString(httpEntity);

        System.out.println("Response body: " + jsonResponds);
        return jsonResponds;
    }

    @PostMapping("/word_practice")
    public String word_practice(@RequestParam(value = "user_id") String user_id) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        log.info("http://47.108.188.196:8000/word_practice?"+"user_id="+user_id);
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/word_practice?"+"user_id="+user_id);
        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        String jsonResponds = EntityUtils.toString(httpEntity);

        System.out.println("Response body: " + jsonResponds);
        return jsonResponds;
    }

    @PostMapping("/word") //返回一个单词
    public String user_practice(@RequestParam(value = "is_six") String is_six,
                                @RequestParam(value = "is_master") String is_master,
                                @RequestParam(value = "is_today") String is_today,
                                @RequestParam(value = "user_id") String user_id) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/word?"+"is_six="+is_six+"&is_master="+is_master+"&is_today="+is_today+"&user_id="+user_id);
        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        String jsonResponds = EntityUtils.toString(httpEntity);

        System.out.println("Response body: " + jsonResponds);
        return jsonResponds;
    }

    @PostMapping("/user_master_six")
    public String user_practice(@RequestParam(value = "user_id") String user_id) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/user_master_six?"+"user_id="+user_id);
        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        String jsonResponds = EntityUtils.toString(httpEntity);

        System.out.println("Response body: " + jsonResponds);
        return jsonResponds;
    }


    @PostMapping("/user_master_four")
    public String user_master_four(@RequestParam(value = "user_id") String user_id) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/user_master_four?"+"user_id="+user_id);
        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        String jsonResponds = EntityUtils.toString(httpEntity);

        System.out.println("Response body: " + jsonResponds);
        return jsonResponds;
    }

    @PostMapping("/word_all_six")
    public String word_all_six() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/word_all_six");
        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        String jsonResponds = EntityUtils.toString(httpEntity);

        System.out.println("Response body: " + jsonResponds);
        return jsonResponds;
    }

    @PostMapping("/word_all_four")
    public String word_all_four() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/word_all_four");
        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        String jsonResponds = EntityUtils.toString(httpEntity);

        System.out.println("Response body: " + jsonResponds);
        return jsonResponds;
    }
    

}
