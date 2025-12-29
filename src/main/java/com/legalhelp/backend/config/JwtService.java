package com.legalhelp.backend.config;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {


    //validate token
    //get all claims by token

    private final String secret = "replace_this_with_a_very_long_secret_key_which_is_at_least_256_bits";
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());
    private final long expirationMs = 1000L * 60  * 50;

    //generate token
    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //get username from token
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    //check token expiry
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, String username) {
        try {
            return username.equals(getUsernameFromToken(token)) && !isTokenExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
