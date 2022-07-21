package com.mst.api;

import com.mst.dto.response.PageResponse;
import com.mst.dto.response.PracticeWorkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/courses")
public interface PracticeWorkApi<PRINCIPAL extends UserDetails> {

    @Operation(summary = "Get user practice work")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "List Practice Work",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PracticeWorkResponse.class))})})
    @GetMapping("/{course_id}/works")
    ResponseEntity<PageResponse<PracticeWorkResponse>> getUserPracticeWorkOnCourse(@PathVariable("course_id") Long courseId,
                                                                                   @RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                                                   @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                                                   @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                                                   @RequestParam(name = "order", required = false, defaultValue = "") String order,
                                                                                   @AuthenticationPrincipal PRINCIPAL userPrincipal);

    @Operation(summary = "Get practice work")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Practice Work",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PracticeWorkResponse.class))})})
    @GetMapping("/works/{work_id}")
    ResponseEntity<PracticeWorkResponse> getPracticeWork(@PathVariable("work_id") Long workId);
}
