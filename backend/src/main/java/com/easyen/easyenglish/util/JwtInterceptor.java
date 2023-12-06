package com.easyen.util;

import com.easyen.util.JwtUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {

    //private static final String TOKEN_HEADER = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //Token为空
        if (token == null || token.isEmpty()) {
            System.out.println("Token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("/login");//回到登录页面
            return false;
        }

        //Token验证失败
        Map<String, Claim> claims = JwtUtil.verifyToken(token);
        if (claims == null) {
            System.out.println("Token超时或验证失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("/login");//回到登录页面
            return false;
        }


        System.out.println("Token验证成功");
        return true;
    }
}
