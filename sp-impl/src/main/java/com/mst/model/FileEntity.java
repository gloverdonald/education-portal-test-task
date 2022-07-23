package com.mst.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_file")
public class FileEntity extends AbstractEntity {

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    @Column(name = "size", nullable = false)
    private Long size;
}
