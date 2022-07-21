package com.mst.service.impl;

import com.mst.dto.request.TokenRefreshRequest;
import com.mst.dto.response.TokensResponse;
import com.mst.dto.response.UserResponse;
import com.mst.exception.UserNotFoundException;
import com.mst.model.RefreshTokenEntity;
import com.mst.model.UserEntity;
import com.mst.repository.RefreshTokenRepository;
import com.mst.repository.UserRepository;
import com.mst.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${expiration.refresh}")
    private Long refreshTokenLifetime;
    @Value("${expiration.access}")
    private Long accessTokenLifetime;
    @Value("${secret}")
    private String secretKey;

    @Override
    public TokensResponse generateTokens(UserResponse userResponse) {
        UserEntity userEntity = userRepository
                .findById(userResponse.getId())
                .orElseThrow(UserNotFoundException::new);

        RefreshTokenEntity refreshToken = generateRefreshToken(userEntity);
        refreshTokenRepository.save(refreshToken);

        return TokensResponse.builder()
                .accessToken("BEARER ".concat(generateAccessToken(userEntity)))
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @Override
    public TokensResponse refreshTokens(TokenRefreshRequest request) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .orElseThrow(UserNotFoundException::new);
        UserEntity userEntity = refreshTokenEntity.getUser();
        refreshTokenEntity.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshTokenEntity);
        return TokensResponse.builder()
                .accessToken("BEARER ".concat(generateAccessToken(userEntity)))
                .refreshToken(refreshTokenEntity.getToken())
                .build();
    }

    private RefreshTokenEntity generateRefreshToken(UserEntity userEntity) {
        return RefreshTokenEntity.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plus(refreshTokenLifetime, ChronoUnit.MILLIS))
                .user(userEntity)
                .build();
    }

    private String generateAccessToken(UserEntity userEntity) {
        Map<String, Object> claims = Jwts.claims();
        claims.put(Claims.SUBJECT, userEntity.getEmail());
        claims.put("roles", List.of(userEntity.getRole().toString()));
        return Jwts.builder()
                .setSubject(userEntity.getEmail())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(accessTokenLifetime)))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }
}