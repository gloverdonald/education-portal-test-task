package com.mst.mapper;

import com.mst.dto.request.CourseRequest;
import com.mst.dto.response.CourseResponse;
import com.mst.model.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = UniversityYearMapper.class)
public interface CourseMapper {
    CourseResponse toResponse(CourseEntity courseEntity);

    List<CourseResponse> toResponse(List<CourseEntity> courseEntities);

    CourseEntity toEntity(CourseRequest courseRequest);

    void update(@MappingTarget CourseEntity courseEntity, CourseRequest courseRequest);
}