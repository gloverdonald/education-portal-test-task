package com.mst.controller;

import com.mst.api.UserApi;
import com.mst.dto.request.AvatarUpdateRequest;
import com.mst.dto.request.CVRequest;
import com.mst.dto.request.PasswordUpdateRequest;
import com.mst.dto.request.UserRequest;
import com.mst.dto.response.FileResponse;
import com.mst.dto.response.SuccessResponse;
import com.mst.dto.response.UserResponse;
import com.mst.security.details.UserDetailsImpl;
import com.mst.service.UserInfoService;
import com.mst.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi<UserDetailsImpl> {

    private final UserService userService;

    private final UserInfoService userInfoService;

    @Override
    public ResponseEntity<UserResponse> getUser(Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }

    @Override
    public ResponseEntity<byte[]> getUserAvatar(Long userId) {
        FileResponse fileResponse = userService.getAvatarByUserId(userId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileResponse.getType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResponse.getName() + "\"").body(fileResponse.getBytes());
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Long userId, UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }


    @Override
    public ResponseEntity<UserResponse> updateUserGroup(Long groupId, UserDetailsImpl userDetailsImpl) {
        return ResponseEntity.ok(userInfoService.updateUserGroup(groupId, userDetailsImpl));
    }

    @Override
    public ResponseEntity<UserResponse> updateUserCV(CVRequest cvRequest, UserDetailsImpl userDetailsImpl) {
        return ResponseEntity.ok(userInfoService.updateUserCV(cvRequest, userDetailsImpl));
    }

    @Override
    public ResponseEntity<UserResponse> updateUserAvatar(AvatarUpdateRequest avatarUpdateRequest, UserDetailsImpl userPrincipal) {
        return ResponseEntity.ok(userInfoService.updateUserAvatar(avatarUpdateRequest, userPrincipal));
    }

    @Override
    public ResponseEntity<SuccessResponse> updateUserPassword(PasswordUpdateRequest passwordUpdateRequest, UserDetailsImpl userDetailsImpl) {
        userService.updatePassword(passwordUpdateRequest, userDetailsImpl);
        return ResponseEntity.ok(SuccessResponse
                .builder()
                .message("Password has been changed")
                .data(Instant.now())
                .build()
        );
    }
}