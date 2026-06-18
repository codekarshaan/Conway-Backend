package com.conway.backend.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    private SecretKey getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(keyBytes);

    }

    public String generateToken(
            String email,
            String role
    ) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("role", role);

        return generateToken(claims, email);

    }
    public String generateToken(
            Map<String, Object> extraClaims,
            String email
    ) {

        return Jwts.builder()
                .claims(extraClaims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + jwtExpiration
                        )
                )
                .signWith(getSigningKey())
                .compact();

    }
    public Claims extractAllClaims(String token) {

        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();

    }
    public <T> T extractClaim(
            String token,
            Function<Claims, T> resolver
    ) {

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);

    }

    public Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );

    }
    public boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());

    }
    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {

        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);

    }

    public String extractRole(String token) {

        return extractClaim(
                token,
                claims -> claims.get("role", String.class)
        );

    }
}