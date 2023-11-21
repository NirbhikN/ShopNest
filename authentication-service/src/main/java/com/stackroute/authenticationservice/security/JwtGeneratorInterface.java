package com.stackroute.authenticationservice.security;
import com.stackroute.authenticationservice.model.User;
import java.util.Map;
import java.util.Objects;

public interface JwtGeneratorInterface {
    Map<String, Object> generateToken(User user);
    void invalidateToken(String token);
    boolean isTokenBlacklisted(String token);
}
