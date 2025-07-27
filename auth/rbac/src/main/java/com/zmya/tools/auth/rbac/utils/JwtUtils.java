package com.zmya.tools.auth.rbac.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtils {

    public static String generateJwt(String username, Integer expirationInSeconds, String secret) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationInSeconds * 1000))
                .signWith(generateSigningKey(secret), SignatureAlgorithm.HS512)
                .compact();
    }

    public static String parseJwt(String token, String secret) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(generateSigningKey(secret))
                .build()
                .parseClaimsJwt(token)
                .getBody();
        if (body.getExpiration().after(new Date())) {
            return null;
        } else {
            return body.getSubject();
        }
    }

    private static Key generateSigningKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private JwtUtils() {
    }

}
