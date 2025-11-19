package com.example.nextshop_api.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nextshop_api.product.dto.ProductDto;
import com.example.nextshop_api.product.dto.ProductOverviewDto;
import com.example.nextshop_api.product.dto.ProductRequestDto;
import com.example.nextshop_api.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {
	private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(ProductRequestDto productRequestDto) {
    	try {
            // 3초(3000ms) 대기
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 인터럽트 상태 복구
        }
        return ResponseEntity.ok(productService.getProducts(productRequestDto));
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductOverviewDto> getProduct(@PathVariable("id") long id) {
    	try {
            // 3초(3000ms) 대기
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 인터럽트 상태 복구
        }
        return ResponseEntity.ok(productService.getProduct(id));
    }
    
}
