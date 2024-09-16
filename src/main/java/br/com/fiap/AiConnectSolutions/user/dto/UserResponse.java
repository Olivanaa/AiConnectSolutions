package br.com.fiap.AiConnectSolutions.user.dto;

import br.com.fiap.AiConnectSolutions.user.User;

import java.time.LocalDateTime;

public record UserResponse (
        Long id,
        String username,
        String email,
        LocalDateTime createdAt

) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
