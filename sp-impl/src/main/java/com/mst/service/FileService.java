package com.mst.service;

import com.mst.dto.response.FileResponse;
import com.mst.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileEntity getById(Long id);

    FileResponse getResponseById(Long id);

    Long save(MultipartFile file);
}
