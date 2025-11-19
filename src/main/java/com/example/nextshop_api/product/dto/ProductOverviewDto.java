package com.example.nextshop_api.product.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ProductOverviewDto {
	private long id;
	private long categoryId;
	private String name;
	private BigDecimal price;
	private String description;
	private String features;
	private String care;
	private int reviewCount;
	private float reviewRating;
	private List<String> images;
	private List<ProductOptionDto> options;
}
