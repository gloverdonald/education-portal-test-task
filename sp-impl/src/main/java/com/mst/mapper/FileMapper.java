package com.mst.mapper;

import com.mst.dto.response.FileInfoResponse;
import com.mst.dto.response.FileResponse;
import com.mst.model.FileEntity;
import com.mst.model.FileStorageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FileMapper {

    FileResponse toResponse(FileStorageEntity file);

    @Mapping(target = "type", source = "mimeType")
    @Mapping(target = "name", source = "originalFileName")
    FileInfoResponse toInfoResponse(FileEntity file);

    @Mapping(target = "type", source = "mimeType")
    @Mapping(target = "name", source = "originalFileName")
    FileResponse toResponse(FileEntity file);
}