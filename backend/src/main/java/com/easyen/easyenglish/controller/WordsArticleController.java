package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.util.JwtUtil;
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
@RequestMapping("/api")
public class WordsArticleController {
    @PostMapping("/article")
    public Result get_article()  {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
            HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/article");
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            String jsonResponds = EntityUtils.toString(httpEntity);
            System.out.println("Response body: " + httpEntity);
            return Result.success(jsonResponds);
        }
         catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }
    // 用户默写单词，之后点击提交,前端判断正确就是已经掌握
    @PostMapping("/user_practice")
    public Result user_practice(@RequestHeader("Authorization") String userJWT,
                                @RequestParam(value = "word_id") String word_id){
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
            log.info("http://47.108.188.196:8000/user_practice?"+"user_id="+user_id+"&word_id="+word_id);
            HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/user_practice?"+"user_id="+user_id+"&word_id="+word_id);
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            String jsonResponds = EntityUtils.toString(httpEntity);
            System.out.println("Response body: " + jsonResponds);
            return Result.success(jsonResponds);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }
    // 获取用户今日掌握的单词数量
    @PostMapping("/word_practice")
    public Result word_practice(@RequestHeader("Authorization") String userJWT) throws Exception {
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        HttpClient httpClient = HttpClientBuilder.create().build();
        try{
            // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
            log.info("http://47.108.188.196:8000/word_practice?"+"user_id="+user_id);
            HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/word_practice?"+"user_id="+user_id);
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();
            String jsonResponds = EntityUtils.toString(httpEntity);

            System.out.println("Response body: " + jsonResponds);
            return Result.success(jsonResponds);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }

    }
    // 根据用户的选择返回一个单词
    @PostMapping("/word") //返回一个单词
    public Result user_practice(@RequestHeader("Authorization") String userJWT,
                                @RequestParam(value = "is_six") String is_six,
                                @RequestParam(value = "is_master") String is_master,
                                @RequestParam(value = "is_today") String is_today
                                ) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        try{
            // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
            HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/word?"+"is_six="+is_six+"&is_master="+is_master+"&is_today="+is_today+"&user_id="+user_id);
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();
            String jsonResponds = EntityUtils.toString(httpEntity);
            System.out.println("Response body: " + jsonResponds);
            return Result.success(jsonResponds);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }

    }
    // 返回用户已经掌握的六级单词
    @PostMapping("/user_master_six")
    public Result user_practice(@RequestHeader("Authorization") String userJWT) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        try{
            // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
            HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/user_master_six?"+"user_id="+user_id);
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();
            String jsonResponds = EntityUtils.toString(httpEntity);

            System.out.println("Response body: " + jsonResponds);
            return Result.success(jsonResponds);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }

    }

    // 返回用户已经掌握的四级单词
    @PostMapping("/user_master_four")
    public Result user_master_four(@RequestHeader("Authorization") String userJWT) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        try{
            // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
            HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/user_master_four?"+"user_id="+user_id);
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();
            String jsonResponds = EntityUtils.toString(httpEntity);

            System.out.println("Response body: " + jsonResponds);
            return Result.success(jsonResponds);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }

    }
    // 获取所有六级单词的数量
    @PostMapping("/word_all_six")
    public Result word_all_six() {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/word_all_six");
        try{
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            String jsonResponds = EntityUtils.toString(httpEntity);

            System.out.println("Response body: " + jsonResponds);
            return Result.success(jsonResponds);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }
    // 获取所有四级单词的数量
    @PostMapping("/word_all_four")
    public Result word_all_four() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        // 创建HttpPost对象并设置URL和请求头信息（Content-Type为application/json）
        HttpGet httpGet = new HttpGet("http://47.108.188.196:8000/word_all_four");
        try{
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            String jsonResponds = EntityUtils.toString(httpEntity);

            System.out.println("Response body: " + jsonResponds);
            return Result.success(jsonResponds);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }

    }
    

}
