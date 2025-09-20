package com.shelfwise.backend.modules.auth.controllers;


import com.shelfwise.backend.modules.auth.service.UserService;
import com.shelfwise.backend.modules.auth.utils.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service){this.service=service;}

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        try{
        UserDto response=service.getUser(id);
        return ResponseEntity.ok(response);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
