package com.example.henryshop_api.user.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.henryshop_api.config.exception.AlreadyExistedUserException;
import com.example.henryshop_api.config.exception.UserNotFoundException;
import com.example.henryshop_api.config.property.ErrorMessagePropertySource;
import com.example.henryshop_api.user.dto.CreateUserDto;
import com.example.henryshop_api.user.dto.UserDto;
import com.example.henryshop_api.user.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final ErrorMessagePropertySource errorMessagePropertySource;

	@Override
	public List<UserDto> getUsers() {
		return userMapper.findAllUsers();
	}

	@Override
	public UserDto getUser(long id) {
		UserDto user = userMapper.findUserById(id);
		if (user == null) {
			throw new UserNotFoundException(errorMessagePropertySource.getUserNotFound());
		}
		return user;
	}

	@Override
	@Transactional
	public void createUser(CreateUserDto createUserDto) {
		UserDto user = getUser(createUserDto.getId());
		if (user != null) {
			throw new AlreadyExistedUserException(errorMessagePropertySource.getAlreadyExistedUser());
		}
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        userMapper.saveUser(createUserDto);
        userMapper.saveUserAuthority(createUserDto);
	}

}
