package com.example.nextshop_api.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.nextshop_api.post.dto.PostDto;

@Mapper
public interface PostMapper {
	List<PostDto> findAllPosts();
	PostDto findPostById(long id);
}
