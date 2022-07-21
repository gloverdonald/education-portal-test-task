package com.mst.mapper;

import com.mst.dto.response.UniversityResponse;
import com.mst.model.UniversityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = UniversityYearMapper.class)
public interface UniversityMapper {
    UniversityResponse toResponse(UniversityEntity university);

    List<UniversityResponse> toResponse(List<UniversityEntity> university);
}