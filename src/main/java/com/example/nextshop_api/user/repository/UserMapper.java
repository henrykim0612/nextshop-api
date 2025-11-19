package com.example.nextshop_api.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.nextshop_api.user.dto.CartDto;
import com.example.nextshop_api.user.dto.CartItemDto;
import com.example.nextshop_api.user.dto.CartOverviewDto;
import com.example.nextshop_api.user.dto.CreateCartDto;
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
	
	CartDto findCartByUserId(long userId);
	void saveCart(CreateCartDto createCartDto);
	void updateCart(long id);
	CartItemDto findCartItemByCartIdAndProductSizeId(@Param("cartId") long cartId, @Param("productSizeId") long productId);
	void updateCartItemQuantity(@Param("id") long id, @Param("quantity") int quantity);
	void saveCartItem(CreateCartDto createCartDto);
	List<CartOverviewDto> findCartOverviewByUserId(long userId);
}
