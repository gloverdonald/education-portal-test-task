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
@Schema(description = "University Year Response")
public class UniversityYearResponse {

    @Schema(description = "University Year id", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "year")
    @JsonProperty("year")
    private Long year;
}