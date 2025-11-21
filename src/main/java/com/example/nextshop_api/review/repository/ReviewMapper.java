package com.example.nextshop_api.review.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.nextshop_api.review.dto.ReviewStatsDto;

@Mapper
public interface ReviewMapper {
	List<ReviewStatsDto> findReviewStatsByProductId(long productId);
}
