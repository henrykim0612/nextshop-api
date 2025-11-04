package com.example.nextshop_api.post.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PostDto {
	private Long id;
	private String title;
	private String description;
	private String postImage;
	private LocalDate dateTime;
	private String authorName;
	private String authorRole;
	private String authorImage;
}
