package com.example.henryshop_api.user.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.henryshop_api.user.dto.CreateUserDto;
import com.example.henryshop_api.user.dto.UserDto;
import com.example.henryshop_api.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
    
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/{id}").buildAndExpand(createUserDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
