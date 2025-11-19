package com.example.nextshop_api.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CartItemDto {
	private long id;
	private long cartId;
	private long productSizeId;
	private int quantity;
	private LocalDateTime createdAt;
}
