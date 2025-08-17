package com.shelfwise.backend.modules.auth.utils.dto;

import com.shelfwise.backend.modules.auth.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class LoginRequest {

    @NotBlank
    @Email
    private String userEmail;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

}
