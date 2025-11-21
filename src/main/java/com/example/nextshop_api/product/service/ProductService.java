package com.example.nextshop_api.product.service;

import java.util.List;

import com.example.nextshop_api.product.dto.ProductDto;
import com.example.nextshop_api.product.dto.ProductOverviewDto;
import com.example.nextshop_api.product.dto.ProductRequestDto;
import com.example.nextshop_api.product.dto.SimpleProductDto;

public interface ProductService {
	List<ProductDto> getProducts(ProductRequestDto productRequestDto);
	ProductOverviewDto getProduct(long id);
	List<SimpleProductDto> getAllProducts();
}
