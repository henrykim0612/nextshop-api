package com.example.nextshop_api.user.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CreateUserDto {
	@JsonIgnore
	private long id;
	
	private String email;
	private String password;
	private String name;
	private String phone;
	private String gender;
	private List<String> visitRoutes;
}
