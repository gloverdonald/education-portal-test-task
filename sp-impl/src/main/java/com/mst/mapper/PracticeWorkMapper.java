package com.mst.mapper;

import com.mst.dto.response.PracticeWorkResponse;
import com.mst.model.PracticeWorkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = FileMapper.class)
public interface PracticeWorkMapper {
    PracticeWorkResponse toResponse(PracticeWorkEntity practiceWorkEntity);

    List<PracticeWorkResponse> toResponse(List<PracticeWorkEntity> practiceWorkEntity);
}
