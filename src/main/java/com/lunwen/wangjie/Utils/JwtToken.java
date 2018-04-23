package com.lunwen.wangjie.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt
 * Created with IDEA
 * author: wangjie
 */
public class JwtToken {

    /**
     * 公用密钥
     */
    public static String SECRET = "TIATNIAN";

    /**
     * 生成token
     * @return
     * @throws Exception
     */
    public static String createToken() throws Exception {
        // 签发时间
        Date iatDate = new Date();

        // 过期时间- 1分钟过期
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DAY_OF_WEEK, 7);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();

        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map) // header
                .withClaim("name", "wangjie")
                .withClaim("age","23")
                .withClaim("org", "wj")
                .withIssuedAt(iatDate) // 设置签名时间
                .sign(Algorithm.HMAC256(SECRET));//加密
        return token;
    }


    /**
     * 解密
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .build();

        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw  new RuntimeException("token过期");
        }
        return jwt.getClaims();
    }
}
