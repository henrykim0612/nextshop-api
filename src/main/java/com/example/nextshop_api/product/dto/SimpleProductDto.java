package com.example.nextshop_api.product.dto;

import lombok.Data;

@Data
public class SimpleProductDto {
	private long id;
	private long categoryId;
	private String name;
}
