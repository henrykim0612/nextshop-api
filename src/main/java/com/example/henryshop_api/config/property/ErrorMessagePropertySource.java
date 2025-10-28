package com.example.henryshop_api.config.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Configuration
@PropertySource(value = "classpath:error-message.properties")
@Getter
public class ErrorMessagePropertySource {
    @Value("${error.message.alreadyExistedUser}")
    private String alreadyExistedUser;
    
    @Value("${error.message.userNotFound}")
    private String userNotFound;
}
