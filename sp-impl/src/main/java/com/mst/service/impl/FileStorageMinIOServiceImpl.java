package com.mst.service.impl;

import com.mst.exception.FileNotFoundException;
import com.mst.service.FileStorageService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.UUID;

@Slf4j
@Primary
@RequiredArgsConstructor
@Service
public class FileStorageMinIOServiceImpl implements FileStorageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    @Override
    public byte[] getById(String id) {
        try {
            InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(id)
                    .build());
            return stream.readAllBytes();
        } catch (MinioException | GeneralSecurityException | IOException e) {
            throw new FileNotFoundException();
        }
    }


    @Override
    public String save(MultipartFile file) {
        String fileId = UUID.randomUUID().toString();
        try {
            log.info("File save: {}", file.getOriginalFilename());
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileId)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build());
            return fileId;
        } catch (MinioException | GeneralSecurityException | IOException e) {
            log.info("File save failed: {}", file.getOriginalFilename(), e);
            throw new FileNotFoundException();
        }
    }
}
