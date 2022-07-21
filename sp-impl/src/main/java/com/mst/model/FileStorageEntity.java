package com.mst.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStorageEntity {
    private String id;

    private String name;

    private String type;

    private String size;

    private byte[] bytes;
}
