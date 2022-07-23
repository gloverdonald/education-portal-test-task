package com.mst.service.impl;

import com.mst.dto.request.AvatarUpdateRequest;
import com.mst.dto.request.CVRequest;
import com.mst.dto.response.UserResponse;
import com.mst.exception.InvalidFileTypeException;
import com.mst.mapper.CVMapper;
import com.mst.mapper.UserMapper;
import com.mst.model.CVEntity;
import com.mst.model.FileEntity;
import com.mst.model.UserEntity;
import com.mst.security.details.UserDetailsImpl;
import com.mst.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserMapper userMapper;

    private final UserService userService;

    private final AcademyGroupService academyGroupService;

    private final SkillService skillService;

    private final CVMapper cvMapper;

    private final FileService fileService;

    @Override
    public UserResponse updateUserGroup(Long groupId, UserDetailsImpl userDetailsImpl) {
        UserEntity userEntity = userDetailsImpl.getUserEntity();
        log.info("User group update: {}", userEntity.getEmail());
        userEntity.setAcademyGroup(academyGroupService.getEntityById(groupId));
        return userMapper.toResponse(userService.save(userEntity));
    }

    @Override
    public UserResponse updateUserCV(CVRequest cvRequest, UserDetailsImpl userDetailsImpl) {
        UserEntity user = userDetailsImpl.getUserEntity();
        log.info("User cv update: {}", user.getEmail());
        CVEntity cv = user.getCv();
        if (cv == null) {
            cv = new CVEntity();
        }
        cvMapper.update(cv, cvRequest);
        if (cvRequest.getCvFileId() != null) {
            FileEntity cvFIle = fileService.getById(cvRequest.getCvFileId());
            cv.setFile(cvFIle);
        }
        if (cvRequest.getSkills() != null) {
            cv.setSkills(skillService.getSkillsByTitles(cvRequest.getSkills()));
        }
        user.setCv(cv);
        return userMapper.toResponse(userService.save(user));
    }

    @Override
    public UserResponse updateUserAvatar(AvatarUpdateRequest avatarUpdateRequest, UserDetailsImpl userDetailsImpl) {
        UserEntity userEntity = userDetailsImpl.getUserEntity();
        log.info("User avatar update: {}", userEntity.getEmail());
        FileEntity file = fileService.getById(avatarUpdateRequest.getUserPhotoId());
        String type = file.getMimeType().split("/")[0];
        if (!type.equals("image")) {
            throw new InvalidFileTypeException();
        }
        userEntity.setAvatar(file);
        return userMapper.toResponse(userService.save(userEntity));
    }
}