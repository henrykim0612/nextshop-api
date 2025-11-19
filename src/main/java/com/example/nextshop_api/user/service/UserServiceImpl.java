package com.example.nextshop_api.user.service;

import java.util.ArrayList;
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
import com.example.nextshop_api.user.dto.CartDto;
import com.example.nextshop_api.user.dto.CartItemDto;
import com.example.nextshop_api.user.dto.CartOverviewDto;
import com.example.nextshop_api.user.dto.CreateCartDto;
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

	@Override
	@Transactional
	public void createCart(CreateCartDto createCartDto) {
		Optional<String> loggedId = SecurityHelper.getLoggedId();
		if (loggedId.isPresent()) {
			UserDto user = this.getUser(loggedId.get());
			if (user != null) {
				createCartDto.setUserId(user.getId());
				CartDto cart = userMapper.findCartByUserId(user.getId());
				if (cart != null) { // 기존에 생성된 카트가 있는 경우
					createCartDto.setCartId(cart.getId());
					userMapper.updateCart(cart.getId());
					// 카트에 담은 동일한 아이템이 있는지 확인
					CartItemDto cartItem = userMapper.findCartItemByCartIdAndProductSizeId(cart.getId(), createCartDto.getProductSizeId());
					if (cartItem != null) {
						userMapper.updateCartItemQuantity(cartItem.getId(), cartItem.getQuantity() + 1);
					} else {
						userMapper.saveCartItem(createCartDto);	
					}					
				} else { // 카트가 없는 경우(카트에 한 번도 담은 적이 없는 경우)
					userMapper.saveCart(createCartDto);	
					userMapper.saveCartItem(createCartDto);
				}
			}
		}
	}

	@Override
	public List<CartOverviewDto> getCartOverview() {
		Optional<String> loggedId = SecurityHelper.getLoggedId();
		if (loggedId.isPresent()) {
			UserDto user = this.getUser(loggedId.get());
			if (user == null) {
				return new ArrayList<>();
			}
			CartDto cart = userMapper.findCartByUserId(user.getId());
			if (cart == null) {
				return new ArrayList<>();
			}
			return userMapper.findCartOverviewByUserId(user.getId());
			
		} else {
			return new ArrayList<>();
		}
	}
}
