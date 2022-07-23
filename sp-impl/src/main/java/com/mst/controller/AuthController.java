package com.mst.controller;

import com.mst.api.AuthApi;
import com.mst.dto.request.NewPasswordRequest;
import com.mst.dto.request.SignUpRequest;
import com.mst.dto.request.TokenRefreshRequest;
import com.mst.dto.request.SignInRequest;
import com.mst.dto.response.TokensResponse;
import com.mst.dto.response.UserResponse;
import com.mst.service.TokenService;
import com.mst.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final UserService userService;

    private final TokenService tokenService;

    @Override
    public ResponseEntity<UserResponse> createUser(SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(signUpRequest));
    }

    @Override
    public ResponseEntity<TokensResponse> login(SignInRequest signInRequest) {
        return ResponseEntity.ok(tokenService.generateTokens(userService.login(signInRequest)));
    }

    @Override
    public ResponseEntity<TokensResponse> confirm(String code, NewPasswordRequest newPasswordRequest) {
        return ResponseEntity.ok(tokenService.generateTokens(userService.confirm(code, newPasswordRequest)));
    }

    @Override
    public ResponseEntity<TokensResponse> refresh(TokenRefreshRequest request) {
        return ResponseEntity.ok(tokenService.refreshTokens(request));
    }
}