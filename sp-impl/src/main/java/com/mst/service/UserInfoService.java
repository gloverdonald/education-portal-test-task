package com.mst.service;

import com.mst.dto.request.AvatarUpdateRequest;
import com.mst.dto.request.CVRequest;
import com.mst.dto.response.UserResponse;
import com.mst.security.details.UserDetailsImpl;

public interface UserInfoService {
    UserResponse updateUserGroup(Long groupId, UserDetailsImpl userDetailsImpl);

    UserResponse updateUserCV(CVRequest cvRequest, UserDetailsImpl userDetailsImpl);

    UserResponse updateUserAvatar(AvatarUpdateRequest avatarUpdateRequest, UserDetailsImpl userPrincipal);
}
