package com.mst.mapper;

import com.mst.dto.response.SkillResponse;
import com.mst.model.SkillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SkillMapper {

    SkillResponse toResponse(SkillEntity skill);
}