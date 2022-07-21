package com.mst.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    byte[] getById(String id);

    String save(MultipartFile file);
}