package com.mst.mapper;

import com.mst.dto.response.AcademyGroupResponse;
import com.mst.model.AcademyGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {UniversityYearMapper.class})
public interface AcademyGroupMapper {


    AcademyGroupResponse toResponse(AcademyGroupEntity academyGroup);

    List<AcademyGroupResponse> toResponse(List<AcademyGroupEntity> academyGroup);
}
