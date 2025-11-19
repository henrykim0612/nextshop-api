package com.example.nextshop_api.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductDto {
	private long id;
	private long categoryId;
	private String name;
	private BigDecimal price;
	private String description;
	private String features;
	private String care;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String thumbnailImageUrl;
	private String colors;
}
