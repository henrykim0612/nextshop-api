package com.example.nextshop_api.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.nextshop_api.review.dto.ReviewStatsDto;
import com.example.nextshop_api.review.repository.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	private final ReviewMapper reviewMapper;
	
	@Override
	public List<ReviewStatsDto> getReviewStats(long productId) {
		return reviewMapper.findReviewStatsByProductId(productId);
	}

}
