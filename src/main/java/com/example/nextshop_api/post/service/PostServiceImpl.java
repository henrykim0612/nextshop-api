package com.example.nextshop_api.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.nextshop_api.post.dto.PostDto;
import com.example.nextshop_api.post.repository.PostMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	private final PostMapper postMapper;

	@Override
	public List<PostDto> getPosts() {
		return postMapper.findAllPosts();
	}

	@Override
	public PostDto getPost(long id) {
		return postMapper.findPostById(id);
	}
}
