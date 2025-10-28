package com.example.henryshop_api.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDto {
	private long id;
	private String email;
	
	@JsonIgnore
	private String password;
	
	private String name;
	private String phone;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private List<String> authorities;
}
