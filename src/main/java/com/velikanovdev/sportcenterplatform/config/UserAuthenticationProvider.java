package com.velikanovdev.sportcenterplatform.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.velikanovdev.sportcenterplatform.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

/**
 * Provider class for user authentication with JWT.
 * This class is responsible for creating and validating JWT tokens.
 */
@Component
public class UserAuthenticationProvider {

    private static final long TOKEN_VALIDITY = 3600000; // 1 hour in milliseconds

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    private Algorithm algorithm;
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Initializes the secret key and algorithm for JWT token generation.
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey);
    }

    /**
     * Creates a JWT token for the given user.
     *
     * @param user the user for whom the token is created
     * @return the JWT token as a string
     */
    public String createToken(UserDTO user) {
        Date now = new Date();
        return JWT.create()
                .withSubject(user.username())
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + TOKEN_VALIDITY))
                .withClaim("email", user.email())
                .withClaim("username", user.username())
                .withClaim("role", user.role().getName())
                .sign(algorithm);
    }

    /**
     * Validates a JWT token and returns an authentication object.
     *
     * @param token the JWT token to validate
     * @return an Authentication object if the token is valid
     * @throws RuntimeException if token validation fails
     */
    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded;
        try {
            decoded = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("Token validation failed");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
