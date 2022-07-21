package com.mst.service.impl;

import com.mst.dto.response.FileResponse;
import com.mst.exception.FileNotFoundException;
import com.mst.mapper.FileMapper;
import com.mst.model.FileEntity;
import com.mst.repository.FileRepository;
import com.mst.service.FileService;
import com.mst.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileStorageService fileStorageService;

    private final FileMapper fileMapper;

    private final FileRepository fileRepository;

    @Override
    public Long save(MultipartFile file) {
        return fileRepository.save(FileEntity.builder()
                .fileId(fileStorageService.save(file))
                .mimeType(file.getContentType())
                .originalFileName(file.getOriginalFilename())
                .size(file.getSize())
                .build()).getId();
    }

    @Override
    public FileEntity getById(Long id) {
        return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public FileResponse getResponseById(Long id) {
        FileEntity file = fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
        FileResponse fileResponse = fileMapper.toResponse(file);
        fileResponse.setBytes(fileStorageService.getById(file.getFileId()));
        return fileResponse;
    }
}
