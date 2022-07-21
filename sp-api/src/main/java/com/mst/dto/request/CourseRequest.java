package com.mst.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Course Request")
public class CourseRequest {

    @Schema(description = "Course name")
    @JsonProperty("name")
    private String courseName;

    @Schema(description = "Course code")
    @JsonProperty("code")
    private String courseCode;

    @Schema(description = "Course description")
    @JsonProperty("description")
    private String description;
}