package com.mst.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User Update CV Request")
public class CVRequest {

    @Schema(description = "cv file id")
    @JsonProperty("cv_file_id")
    private Long cvFileId;

    @Schema(description = "skills")
    @JsonProperty("skills")
    private List<String> skills;

    @Schema(description = "experience desc")
    @JsonProperty("experience_desc")
    private String experienceDesc;

    @Schema(description = "portfolio links")
    @JsonProperty("portfolio_links")
    private String portfolioLinks;
}