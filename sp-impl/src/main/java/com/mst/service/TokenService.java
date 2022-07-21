package com.mst.service;

import com.mst.dto.request.TokenRefreshRequest;
import com.mst.dto.response.TokensResponse;
import com.mst.dto.response.UserResponse;

public interface TokenService {
    TokensResponse generateTokens(UserResponse userResponse);

    TokensResponse refreshTokens(TokenRefreshRequest request);
}