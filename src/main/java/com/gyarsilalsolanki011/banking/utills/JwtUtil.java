package com.gyarsilalsolanki011.banking.utills;

import com.gyarsilalsolanki011.banking.enums.AdminRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private  String SECRET_KEY;

    @Value("${jwt.expiration.time}")
    private long EXPIRATION_TIME;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate Token
    public String generateToken(String username, AdminRole role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role.name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), Jwts.SIG.HS256)  // Updated method
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public AdminRole extractRole(String token) {
        return AdminRole.valueOf(extractClaims(token).get("role", String.class));
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !extractClaims(token).getExpiration().before(new Date());
    }
}
