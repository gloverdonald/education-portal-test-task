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
@Schema(description = "Course Response")
public class CourseResponse {

    @Schema(description = "Course id", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Course name")
    @JsonProperty("course_name")
    private String courseName;

    @Schema(description = "Course code")
    @JsonProperty("course_code")
    private String courseCode;

    @Schema(description = "description")
    @JsonProperty("description")
    private String description;
}