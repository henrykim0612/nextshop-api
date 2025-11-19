package com.example.nextshop_api.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CartOverviewDto {
	private long id;
	private long cartId;
	private long productSizeId;
	private int quantity;
	private LocalDateTime createdAt;
	private long productId;
	private long categoryId;
	private String productName;
	private BigDecimal price;
	private String imageUrl;
	private String altText;
	private String color;
	private String size;
}
