package com.example.nextshop_api.product.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
	private long category;
	private String colors;
	private String sizes;
}
