package com.clem.taskmanager.authentication.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDto {
    @NotEmpty(message = "username required")
    @NotBlank(message = "username required")
    private String username;

    @NotEmpty(message = "password required")
    @NotBlank(message = "password required")
    private String password;
}
