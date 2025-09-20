package com.shelfwise.backend.modules.auth.service;

import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.auth.repository.UserRepository;
import com.shelfwise.backend.modules.auth.utils.dto.UserDto;
import com.shelfwise.backend.modules.auth.utils.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository,UserMapper mapper) {
        this.repo = userRepository;
        this.mapper = mapper;
    }

    public UserDto getUser(Long id) {
        User user = repo.findById(id).orElseThrow(()->new EntityNotFoundException("User not found"));
        return mapper.toUserDto(user) ;
    }

    public User getUserObject(Long id){
        return repo.findById(id).orElseThrow(()->new EntityNotFoundException("User not found"));
    }

}
