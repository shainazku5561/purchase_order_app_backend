package com.ninova.purchaseorderapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenValidator {

    @Value("${jwt.secret}")
    private String secret;  // Secret key as a String

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());  // Convert String to SecretKey
    }

    public boolean validateToken(String token) {
        try {
        	Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            // Token has expired
            return false;
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            // Token is unsupported, malformed, or has an invalid signature
            return false;
        } catch (Exception e) {
            // Any other exceptions
            return false;
        }
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
