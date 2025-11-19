package com.example.nextshop_api.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CreateCartDto {
	@JsonIgnore
	private long cartId;
	
	@JsonIgnore
	private long userId;
	
	private long productSizeId;
}
