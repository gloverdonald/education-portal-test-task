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
@Schema(description = "Academy Group Response")
public class AcademyGroupResponse {

    @Schema(description = "Academy id", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Group name")
    @JsonProperty("name")
    private String groupName;

    @Schema(description = "University year")
    @JsonProperty("university_year")
    private UniversityYearResponse universityYear;
}