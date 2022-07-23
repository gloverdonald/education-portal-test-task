package com.mst.service;

import com.mst.dto.request.CourseRequest;
import com.mst.dto.response.CourseResponse;
import com.mst.dto.response.PageResponse;
import com.mst.model.CourseEntity;

public interface CourseService {
    CourseResponse getCourseResponse(Long id);

    CourseEntity getCourse(Long id);

    PageResponse<CourseResponse> getAllCourseByUniversity(Long universityId, Integer currentPage, Integer sizePage, String param, String order);

    CourseResponse create(CourseRequest courseRequest);

    CourseResponse update(Long id, CourseRequest courseRequest);

    PageResponse<CourseResponse> getAvailableUserCourses(Long userId, Integer currentPage, Integer sizePage, String param, String order);
}