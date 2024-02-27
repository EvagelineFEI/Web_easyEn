package com.easyen.easyenglish.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.easyen.easyenglish.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";

    /**
     * 过期时间
     **/
    private static final long EXPIRATION = 14400L;//单位为秒，4小时

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(User user) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)// 添加头部
                //将基本信息放到claims中
                .withClaim("id", user.getUser_id())//userId
                .withClaim("username", user.getUser_name())//userName
                .withClaim("password", user.getPassword())//password
                .withClaim("email", user.getEmail())
                .withClaim("other_info", user.getOther_info())
                //.withClaim("account_lock_status", user.isAccountLockStatus())
                .withExpiresAt(expireDate) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(SECRET)); //SECRET加密
        return token;
    }


    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("token解码异常");
            return null;
        }
        return jwt.getClaims();
    }

    /**
     * 解析JWT令牌, 并获取用户id
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Integer getUserIdByJWT(String jwt){
//        Claims claims = JWT.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(jwt)
//                .getBody();
//        Integer id = claims.get("id", Integer.class);
//        return id;
        DecodedJWT jwt_ = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(jwt);
        // 获取 claims
        Map<String, Claim> claims = jwt_.getClaims();
        // 从 claims 中获取 user_id
        Integer userId = claims.get("id").asInt();

        // 输出 user_id
        System.out.println("User ID: " + userId);
        return userId;
    }

}
