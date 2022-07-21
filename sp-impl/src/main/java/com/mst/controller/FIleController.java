package com.mst.controller;

import com.mst.api.FileApi;
import com.mst.dto.response.FileResponse;
import com.mst.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FIleController implements FileApi {

    private final FileService fileService;

    @Override
    public ResponseEntity<Long> uploadFile(MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fileService.save(file));
    }

    @Override
    public ResponseEntity<FileResponse> getFileInfo(Long id) {
        return ResponseEntity.ok(fileService.getResponseById(id));
    }

    @Override
    public ResponseEntity<byte[]> showFile(Long id) {
        FileResponse fileResponse = fileService.getResponseById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileResponse.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResponse.getName() + "\"")
                .body(fileResponse.getBytes());
    }
}