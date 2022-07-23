package com.mst.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mst.exception.FileNotFoundException;
import com.mst.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Service
public class FileStorageMongoServiceImpl implements FileStorageService {

    private final GridFsTemplate gridFsTemplate;

    private final GridFsOperations operations;

    @Override
    public String save(MultipartFile file) {
        DBObject metaData = new BasicDBObject();
        metaData.put("size", file.getSize());
        metaData.put("contentType", file.getContentType());
        try {
            log.info("File save: {}", file.getOriginalFilename());
            return gridFsTemplate.store(file.getInputStream(),
                    file.getOriginalFilename() + UUID.randomUUID(),
                    file.getContentType(), metaData).toString();
        } catch (IOException e) {
            log.error("File save failed: {}", file.getOriginalFilename(), e);
            throw new FileNotFoundException();
        }
    }

    @Override
    public byte[] getById(String id) {
        GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (gridFsFile == null) {
            throw new FileNotFoundException();
        }
        try {
            return IOUtils.toByteArray(operations.getResource(gridFsFile).getInputStream());
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
