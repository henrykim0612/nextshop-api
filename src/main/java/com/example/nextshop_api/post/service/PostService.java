package com.example.nextshop_api.post.service;

import java.util.List;

import com.example.nextshop_api.post.dto.PostDto;

public interface PostService {
	List<PostDto> getPosts();
	PostDto getPost(long id);
}
