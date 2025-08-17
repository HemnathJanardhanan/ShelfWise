package com.shelfwise.backend.modules.auth.utils.dto;

import com.shelfwise.backend.modules.auth.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
public class RegisterRequest {
    @NotBlank(message = "Username must not be blank")
    private String userName;

    @Email(message = "Email must be valid")
    @NotNull(message = "Email must not be blank")
    private String userEmail;

    @NotNull(message = "Password must not be blank")
    private String password;

    @NotNull(message = "Role must be provided")
    private Role role;

}
