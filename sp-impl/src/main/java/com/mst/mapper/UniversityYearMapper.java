package com.mst.mapper;

import com.mst.dto.response.UniversityYearResponse;
import com.mst.model.UniversityEntity;
import com.mst.model.UniversityYearEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = UniversityEntity.class)
public interface UniversityYearMapper {
    UniversityYearResponse toResponse(UniversityYearEntity universityYear);

    List<UniversityYearResponse> toResponse(List<UniversityYearEntity> universityYear);
}