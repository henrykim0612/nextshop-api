package com.example.henryshop_api.user.service;

import java.util.List;

import com.example.henryshop_api.user.dto.CreateUserDto;
import com.example.henryshop_api.user.dto.UserDto;

public interface UserService {
	List<UserDto> getUsers();
	UserDto getUser(long id);
	void createUser(CreateUserDto createUserDto);
}
