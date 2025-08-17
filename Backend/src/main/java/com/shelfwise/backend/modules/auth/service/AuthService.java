package com.shelfwise.backend.modules.auth.service;

import com.shelfwise.backend.modules.auth.model.Status;
import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.auth.repository.UserRepository;
import com.shelfwise.backend.modules.auth.utils.dto.LoginRequest;
import com.shelfwise.backend.modules.auth.utils.dto.RegisterRequest;
import com.shelfwise.backend.modules.auth.utils.dto.UserDto;
import com.shelfwise.backend.modules.auth.utils.mapper.UserMapper;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository repo;
    private UserMapper userMapper;

    public AuthService(UserRepository repo, UserMapper userMapper) {
        this.repo = repo;
        this.userMapper = userMapper;
    }

    public UserDto register(RegisterRequest request){
        if (repo.findByUserEmail(request.getUserEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        User newUser = userMapper.toUser(request);
        newUser.setStatus(Status.ACTIVE);
        User saved= repo.save(newUser);
        return userMapper.toUserDto(saved);

    }

    public UserDto login(LoginRequest request){
        User validUser=repo.findByUserEmailAndPassword(request.getUserEmail(), request.getPassword()).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
        return userMapper.toUserDto(validUser);
    }

}
