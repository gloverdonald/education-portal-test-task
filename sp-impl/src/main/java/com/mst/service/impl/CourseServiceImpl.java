package com.mst.service.impl;

import com.mst.dto.request.CourseRequest;
import com.mst.dto.response.CourseResponse;
import com.mst.dto.response.PageResponse;
import com.mst.exception.CourseNotFoundException;
import com.mst.mapper.CourseMapper;
import com.mst.model.CourseEntity;
import com.mst.repository.CourseRepository;
import com.mst.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.mst.repository.specification.CourseSpecification.byUserId;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    private final CourseRepository courseRepository;

    @Override
    public CourseResponse getCourseResponse(Long id) {
        return courseMapper.toResponse(courseRepository.findById(id).orElseThrow(CourseNotFoundException::new));
    }

    @Override
    public CourseEntity getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public PageResponse<CourseResponse> getAllCourseByUniversity(Long universityId, Integer currentPage, Integer sizePage, String param, String order) {
        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }
        Page<CourseEntity> courseEntityPage = courseRepository.findAllByUniversityId(universityId, PageRequest.of(currentPage, sizePage, sort));
        return PageResponse.<CourseResponse>builder()
                .currentPage(currentPage)
                .totalPages(courseEntityPage.getTotalPages())
                .totalElements(courseEntityPage.getTotalElements())
                .responses(courseMapper.toResponse(courseEntityPage.getContent()))
                .build();
    }

    @Override
    public CourseResponse create(CourseRequest courseRequest) {
        return courseMapper.toResponse(courseRepository.save(courseMapper.toEntity(courseRequest)));
    }

    @Override
    public CourseResponse update(Long id, CourseRequest courseRequest) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
        courseMapper.update(course, courseRequest);
        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    public PageResponse<CourseResponse> getAvailableUserCourses(Long userId, Integer currentPage, Integer sizePage, String param, String order) {
        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }
        Specification<CourseEntity> specification = byUserId(userId);
        Page<CourseEntity> courseEntityPage = courseRepository.findAll(specification, PageRequest.of(currentPage, sizePage, sort));
        return PageResponse.<CourseResponse>builder()
                .totalPages(courseEntityPage.getTotalPages())
                .totalElements(courseEntityPage.getTotalElements())
                .currentPage(currentPage)
                .responses(courseMapper.toResponse(courseEntityPage.getContent()))
                .build();
    }
}
