package com.example.nextshop_api.review.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nextshop_api.review.dto.ReviewStatsDto;
import com.example.nextshop_api.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {
	private final ReviewService reviewService;

	@GetMapping("/admin/review-stats/{productId}")
    public ResponseEntity<List<ReviewStatsDto>> getUser(@PathVariable("productId") long productId) {
        return ResponseEntity.ok(reviewService.getReviewStats(productId));
    }
}
