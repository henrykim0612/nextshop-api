package com.example.nextshop_api.product.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.nextshop_api.product.dto.ProductDto;
import com.example.nextshop_api.product.dto.ProductOverviewDto;
import com.example.nextshop_api.product.dto.ProductRequestDto;
import com.example.nextshop_api.product.dto.SimpleProductDto;

@Mapper
public interface ProductMapper {
	List<ProductDto> findProductsByCategory(ProductRequestDto productRequestDto);
	ProductOverviewDto findProductById(long id);
	List<SimpleProductDto> findAllProducts();
}
