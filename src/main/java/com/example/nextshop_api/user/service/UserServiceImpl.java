package com.example.nextshop_api.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nextshop_api.config.exception.AlreadyExistedUserException;
import com.example.nextshop_api.config.exception.UserNotFoundException;
import com.example.nextshop_api.config.jwt.TokenProvider;
import com.example.nextshop_api.config.property.ErrorMessagePropertySource;
import com.example.nextshop_api.helper.SecurityHelper;
import com.example.nextshop_api.user.dto.CreateUserDto;
import com.example.nextshop_api.user.dto.SignInDto;
import com.example.nextshop_api.user.dto.UserDto;
import com.example.nextshop_api.user.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final ErrorMessagePropertySource errorMessagePropertySource;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

	@Override
	public List<UserDto> getUsers() {
		return userMapper.findAllUsers();
	}

	@Override
	public UserDto getUser(String email) {
		UserDto user = userMapper.findUserByEmail(email);
		if (user == null) {
			throw new UserNotFoundException(errorMessagePropertySource.getUserNotFound());
		}
		return user;
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
        UserDto user = userMapper.findUserByEmail(createUserDto.getEmail());
		if (user != null) {
			throw new AlreadyExistedUserException(errorMessagePropertySource.getAlreadyExistedUser());
		}
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        userMapper.saveUser(createUserDto);
        userMapper.saveUserAuthority(createUserDto);
        if (createUserDto.getVisitRoutes().size() > 0) {
        	userMapper.saveVisitRoutes(createUserDto);	
        }
	}

    @Override
    public String createToken(SignInDto signInDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // authentication 객체를 createToken 메소드를 통해서 JWT Token 을 생성
            return tokenProvider.createToken(authentication);

        } catch (Exception ex) {
            throw new BadCredentialsException(errorMessagePropertySource.getBadCredentials());
        }
    }

    @Override
    public Optional<UserDto> getLoggedUser() {
        Optional<String> loggedId = SecurityHelper.getLoggedId();
        return loggedId.map(userMapper::findUserByEmail);
    }
}
