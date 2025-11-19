package com.example.nextshop_api.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CartDto {
	private long id;
	private long userId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
