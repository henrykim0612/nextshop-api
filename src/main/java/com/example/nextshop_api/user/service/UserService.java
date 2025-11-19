package com.example.nextshop_api.user.service;

import java.util.List;
import java.util.Optional;

import com.example.nextshop_api.user.dto.CartOverviewDto;
import com.example.nextshop_api.user.dto.CreateCartDto;
import com.example.nextshop_api.user.dto.CreateUserDto;
import com.example.nextshop_api.user.dto.SignInDto;
import com.example.nextshop_api.user.dto.UserDto;

public interface UserService {
	List<UserDto> getUsers();
	UserDto getUser(String email);
	UserDto getUser(long id);
	void createUser(CreateUserDto createUserDto);
    String createToken(SignInDto signInDto);
    Optional<UserDto> getLoggedUser();
    void createCart(CreateCartDto createCartDto);
    List<CartOverviewDto> getCartOverview();
}
