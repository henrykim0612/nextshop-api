package com.example.henryshop_api.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.henryshop_api.user.dto.CreateUserDto;
import com.example.henryshop_api.user.dto.UserDto;

@Mapper
public interface UserMapper {
	List<UserDto> findAllUsers();
	UserDto findUserById(long id);
	void saveUser(CreateUserDto createUserDto);
	void saveUserAuthority(CreateUserDto createUserDto);
}
