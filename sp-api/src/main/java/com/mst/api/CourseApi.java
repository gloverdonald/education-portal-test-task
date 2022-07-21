package com.mst.api;

import com.mst.dto.request.CourseRequest;
import com.mst.dto.response.CourseResponse;
import com.mst.dto.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/courses")
public interface CourseApi<PRINCIPAL extends UserDetails> {

    @Operation(summary = "Create new course")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "New course",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponse.class))})})
    @PostMapping
    ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest);

    @Operation(summary = "Get course")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Course",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponse.class))})})
    @GetMapping("/{id}")
    ResponseEntity<CourseResponse> getCourse(@PathVariable Long id);

    @Operation(summary = "Update course")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Updated course",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponse.class))})})
    @PutMapping("/{id}")
    ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest);

    @Operation(summary = "Get all courses")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Course",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponse.class))}),})
    @GetMapping("/{university_id}/university")
    ResponseEntity<PageResponse<CourseResponse>> getAllCourseByUniversity(@PathVariable("university_id") Long universityId,
                                                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                                          @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                                          @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                                          @RequestParam(name = "order", required = false, defaultValue = "") String order);


    @Operation(summary = "Get current user all courses")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Course",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponse.class))}),})
    @GetMapping("/user/{user-id}")
    ResponseEntity<PageResponse<CourseResponse>> getAvailableUserCourse(@PathVariable("user-id") Long userId,
                                                                        @RequestParam(name = "page", required = false, defaultValue = "0") Integer currentPage,
                                                                        @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizePage,
                                                                        @RequestParam(name = "param", required = false, defaultValue = "id") String param,
                                                                        @RequestParam(name = "order", required = false, defaultValue = "") String order);
}