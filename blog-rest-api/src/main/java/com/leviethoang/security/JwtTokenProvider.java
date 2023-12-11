package com.leviethoang.security;

import com.leviethoang.exception.BlogApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtTokenProvider {

    @Value("${spring.jwt.secret.key}")
    private String secretKey;
    @Value("${spring.jwt.expiration.time}")
    private Long expirationTime;

    public String token(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + expirationTime);
        String token = Jwts.builder().setSubject(username)
                .setExpiration(expirationDate)
                .setIssuedAt(new Date())
                .signWith(key())
                .compact();
        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String getUsername(String token){
        Claims claim = Jwts.parser().setSigningKey(key())
                .build().parseClaimsJws(token)
                .getBody();
        String username =  claim.getSubject();
        return username;
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(key())
                    .build().parse(token);
            return true;
        }catch (MalformedJwtException ex){
            throw new BlogApiException("Invalid JWT token");
        }catch (ExpiredJwtException ex){
            throw new BlogApiException("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            throw new BlogApiException("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            throw new BlogApiException("JWT claims string is empty");
        }
    }

}
