package com.example.nextshop_api.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.nextshop_api.user.dto.CreateUserDto;
import com.example.nextshop_api.user.dto.UserDto;

@Mapper
public interface UserMapper {
	List<UserDto> findAllUsers();
	UserDto findUserByEmail(String email);
	UserDto findUserById(long id);
	void saveUser(CreateUserDto createUserDto);
	void saveVisitRoutes(CreateUserDto createUserDto);
	void saveUserAuthority(CreateUserDto createUserDto);
}
