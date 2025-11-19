package com.example.nextshop_api.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.nextshop_api.product.dto.ProductDto;
import com.example.nextshop_api.product.dto.ProductOverviewDto;
import com.example.nextshop_api.product.dto.ProductRequestDto;
import com.example.nextshop_api.product.repository.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	private final ProductMapper productMapper;

	@Override
	public List<ProductDto> getProducts(ProductRequestDto productRequestDto) {
		return productMapper.findProductsByCategory(productRequestDto);
	}

	@Override
	public ProductOverviewDto getProduct(long id) {
		return productMapper.findProductById(id);
	}
}
