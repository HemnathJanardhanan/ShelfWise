package com.shelfwise.backend.modules.auth.utils.dto;

import com.shelfwise.backend.modules.auth.model.Role;
import com.shelfwise.backend.modules.auth.model.Status;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String userEmail;
    private Role role;
    private Status status;
}
