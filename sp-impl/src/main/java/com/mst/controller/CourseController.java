package com.mst.controller;

import com.mst.api.CourseApi;
import com.mst.dto.request.CourseRequest;
import com.mst.dto.response.CourseResponse;
import com.mst.dto.response.PageResponse;
import com.mst.security.details.UserDetailsImpl;
import com.mst.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CourseController implements CourseApi<UserDetailsImpl> {

    private final CourseService courseService;

    @Override
    public ResponseEntity<CourseResponse> createCourse(CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.create(courseRequest));
    }

    @Override
    public ResponseEntity<CourseResponse> getCourse(Long id) {
        return ResponseEntity.ok(courseService.getCourseResponse(id));
    }

    @Override
    public ResponseEntity<CourseResponse> updateCourse(Long id, CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.update(id, courseRequest));
    }

    @Override
    public ResponseEntity<PageResponse<CourseResponse>> getAllCourseByUniversity(Long universityId, Integer currentPage, Integer sizePage, String param, String order) {
        return ResponseEntity.ok(courseService.getAllCourseByUniversity(universityId, currentPage, sizePage, param, order));
    }

    @Override
    public ResponseEntity<PageResponse<CourseResponse>> getAvailableUserCourse(Long userId, Integer currentPage, Integer sizePage, String param, String order) {
        return ResponseEntity.ok(courseService.getAvailableUserCourses(userId, currentPage, sizePage, param, order));
    }
}
