package com.example.nextshop_api.user.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.nextshop_api.config.jwt.TokenDto;
import com.example.nextshop_api.helper.CookieHelper;
import com.example.nextshop_api.user.dto.CreateUserDto;
import com.example.nextshop_api.user.dto.SignInDto;
import com.example.nextshop_api.user.dto.UserDto;
import com.example.nextshop_api.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final CookieHelper cookieHelper;

    @GetMapping("/admin/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
    
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/{id}").buildAndExpand(createUserDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) {
        String token = userService.createToken(signInDto);

        // 쿠키에 토큰 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", cookieHelper.makeJwtCookie(token));

        return new ResponseEntity<>(TokenDto.builder().token(token).build(), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut() {
        HttpHeaders httpHeaders = new HttpHeaders();
        cookieHelper.deleteJwtCookie(httpHeaders);
        return ResponseEntity.ok().headers(httpHeaders).body(null);
    }

    @GetMapping("/users/me")
    public ResponseEntity<Optional<UserDto>> getLoggedUser() {
        return ResponseEntity.ok(userService.getLoggedUser());
    }
}
