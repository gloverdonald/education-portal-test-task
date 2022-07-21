package com.mst.service;

import com.mst.dto.request.*;
import com.mst.dto.response.FileResponse;
import com.mst.dto.response.UserResponse;
import com.mst.model.UserEntity;
import com.mst.security.details.UserDetailsImpl;

public interface UserService {
    UserResponse createUser(SignUpRequest signUpRequest);

    UserResponse login(SignInRequest signInRequest);

    UserResponse confirm(String confirmCode, NewPasswordRequest passwordRequest);

    UserResponse getById(Long id);

    FileResponse getAvatarByUserId(Long id);

    UserResponse updateUser(Long userId, UserRequest userRequest);

    UserEntity save(UserEntity userEntity);

    void updatePassword(PasswordUpdateRequest passwordUpdateRequest, UserDetailsImpl userDetailsImpl);
}