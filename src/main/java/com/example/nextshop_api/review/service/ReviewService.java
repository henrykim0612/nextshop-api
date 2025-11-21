package com.example.nextshop_api.review.service;

import java.util.List;

import com.example.nextshop_api.review.dto.ReviewStatsDto;

public interface ReviewService {
	List<ReviewStatsDto> getReviewStats(long productId);
}
