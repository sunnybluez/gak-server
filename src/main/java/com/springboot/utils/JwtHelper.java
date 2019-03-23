package com.springboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * JWT token生成和解析的工具类
 */
public class JwtHelper {


    final static String base64EncodedSecretKey = "mySecretKey";//私钥

    final static long TOKEN_EXP = 1000 * 60 * 15;//过期时间十五分钟


    /**
     * 生成token
     * @param userName
     * @param identity
     * @return token
     */
    public static String getToken(String userName, String identity) {

        return Jwts.builder()
                .setSubject(userName)
                .claim("identity", identity)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();

    }

    /**
     * 解析Token
     * @param jsonWebToken
     * @return 有关claim的键值对的map
     */
    public static Claims parseToken(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64EncodedSecretKey))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            System.out.println("tokenyichang");
            return null;
        }
    }



}
