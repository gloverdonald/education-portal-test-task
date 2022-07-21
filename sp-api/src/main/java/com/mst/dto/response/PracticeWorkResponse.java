package com.mst.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Practice Work Response")
public class PracticeWorkResponse {

    @Schema(description = "Practice Work id", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Practice work title")
    @JsonProperty("title")
    private String title;

    @Schema(description = "Practice work general info")
    @JsonProperty("general_info")
    private String generalInfo;

    @Schema(description = "Practice work technical requirements")
    @JsonProperty("technical_requirements")
    private String technicalRequirements;

    @Schema(description = "Practice work files")
    @JsonProperty("files")
    private List<FileInfoResponse> files;
}