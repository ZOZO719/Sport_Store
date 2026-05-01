package com.store.store.service.auth;

import java.util.Date;
import java.util.HexFormat;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    private static final String SECRET =
        "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final long ACCESS_EXPIRY  = 1000L * 60 * 15;           // 15 min
    private static final long REFRESH_EXPIRY = 1000L * 60 * 60 * 24 * 7; // 7 days

    // BUG FIX: return type must be SecretKey (not Key) to use with .verifyWith()
    private SecretKey signingKey() {
        byte[] keyBytes = HexFormat.of().parseHex(SECRET);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateAccessToken(String email) {
        return buildToken(email, ACCESS_EXPIRY);
    }

    public String generateRefreshToken(String email) {
        return buildToken(email, REFRESH_EXPIRY);
    }

    private String buildToken(String email, long expiry) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(SignatureAlgorithm.HS256, signingKey())
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String email) {
        try {
            return extractEmail(token).equals(email)
                && !extractClaims(token, Claims::getExpiration).before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey())
                .parseClaimsJws(token)
                .getBody();
    }
}