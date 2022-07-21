package com.mst.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resume Response")
public class CVResponse {

    @Schema(description = "CV file id")
    @JsonProperty("cv_file_id")
    private Long cvFileId;

    @Schema(description = "CV skills")
    @JsonProperty("skills")
    private Set<SkillResponse> skills;

    @Schema(description = "CV portfolio links")
    @JsonProperty("portfolio_links")
    private String portfolioLinks;

    @Schema(description = "CV experience desc")
    @JsonProperty("experience_desc")
    private String experienceDesc;
}