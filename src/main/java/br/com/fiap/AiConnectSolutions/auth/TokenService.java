package br.com.fiap.AiConnectSolutions.auth;

import br.com.fiap.AiConnectSolutions.user.User;
import br.com.fiap.AiConnectSolutions.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final UserRepository userRepository;
    private Algorithm algorithm;

    public TokenService(UserRepository userRepository, @Value("${jwt.secret}") String secret) {
        this.userRepository = userRepository;
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public Token createToken(User user) {
        var expiresAt = LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.ofHours(-3));
        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(expiresAt)
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole())
                .sign(algorithm);

        return new Token(token, user.getEmail(), user.getId().toString(), user.getRole());
    }

    public User getUserFromToken(String token) {
        var userId = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        return userRepository
                .findById(Long.parseLong(userId))
                .orElseThrow( () -> new UsernameNotFoundException("User not found"));
    }
}
