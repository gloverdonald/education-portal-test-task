package com.mst.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "File Response")
public class FileResponse {

    @Schema(description = "File name", example = "image.png")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Type name", example = "image/jpeg")
    @JsonProperty("type")
    private String type;

    @Schema(description = "Type size", example = "4102501")
    @JsonProperty("size")
    private String size;

    @Schema(description = "FIle bytes")
    @JsonProperty("bytes")
    private byte[] bytes;
}