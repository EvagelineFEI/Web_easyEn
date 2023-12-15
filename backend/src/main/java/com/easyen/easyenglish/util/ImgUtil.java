package com.example.foodiedemo.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 图像会用到的一些函数
 */
@Component
public class ImgUtil {
    private static final String testUrl = "https://gpt.leafqycc.top:6660/login/image?imageName=";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // 便于图像访问的url
    public static String getUrl() {
        return testUrl;
    }

    // 生成图像用到的随机字符串
    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}