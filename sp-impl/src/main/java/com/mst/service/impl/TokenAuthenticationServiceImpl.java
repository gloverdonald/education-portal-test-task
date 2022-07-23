package com.mst.service.impl;


import com.mst.dto.response.UserResponse;
import com.mst.exception.UserNotFoundException;
import com.mst.mapper.UserMapper;
import com.mst.repository.UserRepository;
import com.mst.service.TokenAuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @Value("${secret}")
    private String secretKey;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserResponse getUserInfoByToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
        String email = claims.getSubject();
        return userMapper.toResponse(userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new));
    }
}