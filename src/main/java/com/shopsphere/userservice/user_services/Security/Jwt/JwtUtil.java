package com.shopsphere.userservice.user_services.Security.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private  String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
//        System.out.println(jwtSecret); its just for debugging
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username, UUID userId) {
        return Jwts.builder()
                .subject(username)
                .claim("userId",userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ jwtExpiration))
                .signWith(secretKey).compact();
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, String username){
     try {
         String extracted = extractUsername(token);
         return extracted.equals(username) && !isTokenExpired(token);
     }
     catch (Exception e) {
         return false;
     }
    }
    private boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
    private Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserId(String token){
        return extractClaims(token).get("userId", String.class);
    }

}
