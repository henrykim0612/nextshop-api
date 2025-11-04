package com.example.nextshop_api.post.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nextshop_api.post.dto.PostDto;
import com.example.nextshop_api.post.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {
	private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
    	try {
            // 3초(3000ms) 대기
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 인터럽트 상태 복구
        }
        return ResponseEntity.ok(postService.getPosts());
    }
    
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") long id) {
    	return ResponseEntity.ok(postService.getPost(id));
    }
}
