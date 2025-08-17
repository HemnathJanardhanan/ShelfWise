package com.shelfwise.backend.modules.auth.controllers;


import com.shelfwise.backend.modules.auth.service.AuthService;
import com.shelfwise.backend.modules.auth.utils.dto.LoginRequest;
import com.shelfwise.backend.modules.auth.utils.dto.RegisterRequest;
import com.shelfwise.backend.modules.auth.utils.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequest request){
        UserDto response=authService.register(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginRequest request){
        UserDto response=authService.login(request);
        return ResponseEntity.ok(response);
    }

}
