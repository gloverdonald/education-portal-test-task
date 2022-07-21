package com.mst.mapper;

import com.mst.dto.request.SignUpRequest;
import com.mst.dto.request.UserRequest;
import com.mst.dto.response.UserResponse;
import com.mst.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {CVMapper.class, AcademyGroupMapper.class})
public interface UserMapper {

    @Mapping(target = "avatarId", source = "avatar.id")
    UserResponse toResponse(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserEntity toEntity(SignUpRequest SIgnUpRequest);

    @Mapping(target = "birthDate", source = "birthdate")
    void update(@MappingTarget UserEntity user, UserRequest userRequest);
}