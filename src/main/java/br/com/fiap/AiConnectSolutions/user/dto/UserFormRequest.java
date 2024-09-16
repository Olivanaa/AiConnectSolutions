package br.com.fiap.AiConnectSolutions.user.dto;

import br.com.fiap.AiConnectSolutions.user.User;

import java.time.LocalDateTime;

public record UserFormRequest(
        String name,
        String email,
        String password,
        String role
) {
    public User toModel(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(role)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
