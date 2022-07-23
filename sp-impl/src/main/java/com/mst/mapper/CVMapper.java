package com.mst.mapper;

import com.mst.dto.request.CVRequest;
import com.mst.dto.response.CVResponse;
import com.mst.model.CVEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {SkillMapper.class})
public interface CVMapper {
    @Mapping(target = "cvFileId", source = "file.id")
    CVResponse toResponse(CVEntity cv);

    @Mapping(target = "skills", ignore = true)
    void update(@MappingTarget CVEntity cv, CVRequest cVRequest);
}