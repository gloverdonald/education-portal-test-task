package com.mst.service;

import com.mst.dto.response.UserResponse;

public interface TokenAuthenticationService {
    UserResponse getUserInfoByToken(String token);
}