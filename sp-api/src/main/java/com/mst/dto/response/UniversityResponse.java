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
@Schema(description = "University Response")
public class UniversityResponse {

    @Schema(description = "University id", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "University name")
    @JsonProperty("university_name")
    private String universityName;

    @Schema(description = "University code")
    @JsonProperty("university_code")
    private String universityCode;
}