package com.stackroute.authenticationservice.security;
import com.stackroute.authenticationservice.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface{
    private String message;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;
//    @Override
//    public Map<String, Object> generateToken(User user) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("customClaimKey", "customClaimValue");
//        String jwtToken="";
//        jwtToken = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
//        Map<String, Object> jwtTokenGen = new HashMap<>();
//        jwtTokenGen.put("token", jwtToken);
//        jwtTokenGen.put("Token Generated", message);
//
//        return jwtTokenGen;
//
//    }

    private final Set<String> blacklistedTokens = new HashSet<>();


    @Override
    public Map<String, Object> generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("name", user.getName());
        String jwtToken="";
        jwtToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
                Map<String, Object> jwtTokenGen = new HashMap<>();
                jwtTokenGen.put("token", jwtToken);
                jwtTokenGen.put("Token Generated", message);
                return jwtTokenGen;
    }

    public void invalidateToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}

