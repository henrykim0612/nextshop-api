package com.example.nextshop_api.product.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductOptionDto {
	private String color;
	private List<ProductSizeDto> sizes;
}
