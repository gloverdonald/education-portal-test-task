package com.mst.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${minio.url}")
    private String minioClientEndpoint;

    @Value("${minio.secret-key}")
    private String minioUsername;

    @Value("${minio.access-key}")
    private String minioPassword;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioClientEndpoint)
                .credentials(minioUsername, minioPassword)
                .build();
    }
}
