package com.store.store.service.auth;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET =
        "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final long ACCESS_EXPIRY  = 1000L * 60 * 15;        // 15 دقيقة
    private static final long REFRESH_EXPIRY = 1000L * 60 * 60 * 24 * 7; // 7 أيام

    private Key signingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(String email) {
        return buildToken(email, ACCESS_EXPIRY);
    }

    public String generateRefreshToken(String email) {
        return buildToken(email, REFRESH_EXPIRY);
    }

    private String buildToken(String email, long expiry) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(signingKey())
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaims(token , Claims::getSubject);
    }

    public boolean isTokenValid(String token, String email) {
        try {
            return extractEmail(token).equals(email)
                && !extractClaims(token).getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }
    public <T> T extractClaims(String token , Function<Claims, T> claimsReslover){
        final Claims claims = extractAllClaims(token);
        return claimsReslover.apply(claims);
    }

    // private — مش محتاج يكون public
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey())
                .build() 
                .parseSignedClaims(token)
                .getPayload();
    }
}