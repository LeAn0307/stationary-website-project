package com.shinhands.mu.Stationary.security;

import com.shinhands.mu.Stationary.exception.JwtTokenMalformedException;
import com.shinhands.mu.Stationary.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

//    @Value("${jwt.token.validity}")
//    private long tokenValidity;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor("12345678910111211234567891011121".getBytes());
    }

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public Key getSecretKey() {
        return key;
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 6000000);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public void validateToken(final String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }
}