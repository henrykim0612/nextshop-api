package com.example.nextshop_api.config.security;

import com.example.nextshop_api.user.dto.UserDto;
import com.example.nextshop_api.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        UserDto user = userMapper.findUserByEmail(email);
        List<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities().stream().map(SimpleGrantedAuthority::new).toList();
        return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
