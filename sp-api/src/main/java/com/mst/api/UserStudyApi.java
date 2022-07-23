package com.mst.api;

import com.mst.dto.response.AcademyGroupResponse;
import com.mst.dto.response.PageResponse;
import com.mst.dto.response.UniversityResponse;
import com.mst.dto.response.UniversityYearResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/users/study")
public interface UserStudyApi {
    @Operation(summary = "Get all university")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "University",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UniversityResponse.class))}),})
    @GetMapping("/universities")
    ResponseEntity<PageResponse<UniversityResponse>> getAllUniversity(@RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                                      @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                                      @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                                      @RequestParam(name = "order", required = false, defaultValue = "") String order,
                                                                      @RequestParam(name = "search", required = false, defaultValue = "") String searchValue);

    @Operation(summary = "Get university year (University course) by University")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "University Year(University course)",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UniversityResponse.class))}),})
    @GetMapping("/universities/{university_id}/years")
    ResponseEntity<PageResponse<UniversityYearResponse>> getUniversity(@PathVariable("university_id") Long universityId,
                                                                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                                       @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                                       @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                                       @RequestParam(name = "order", required = false, defaultValue = "") String order);

    @Operation(summary = "Get all academy groups by university year")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Academy Group",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AcademyGroupResponse.class))}),})
    @GetMapping("years/{year_id}/academy-groups")
    ResponseEntity<PageResponse<AcademyGroupResponse>> getAllAcademyGroup(@PathVariable("year_id") Long universityYearId,
                                                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                                          @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                                          @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                                          @RequestParam(name = "order", required = false, defaultValue = "") String order,
                                                                          @RequestParam(name = "search", required = false, defaultValue = "") String searchValue);
}
