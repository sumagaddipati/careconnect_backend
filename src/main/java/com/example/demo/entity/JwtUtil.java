package com.example.demo.entity;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "careconnectcareconnectcareconnectcareconnect"; // 32+ chars

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                    new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
                )
                .signWith(getKey())
                .compact();
    }
}
