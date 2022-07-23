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
@Schema(description = "Page Response")
public class PageResponse<T> {

    @Schema(description = "List of responses")
    @JsonProperty("responses")
    private List<T> responses;

    @Schema(description = "Current page")
    @JsonProperty("current_page")
    private Integer currentPage;

    @Schema(description = "Total pages")
    @JsonProperty("total_pages")
    private Integer totalPages;

    @Schema(description = "Total Elements")
    @JsonProperty("total_elements")
    private Long totalElements;

}
