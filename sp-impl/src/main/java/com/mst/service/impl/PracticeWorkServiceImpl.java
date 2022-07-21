package com.mst.service.impl;

import com.mst.dto.response.PageResponse;
import com.mst.dto.response.PracticeWorkResponse;
import com.mst.exception.PracticeWorkNotFoundException;
import com.mst.mapper.PracticeWorkMapper;
import com.mst.model.PracticeWorkEntity;
import com.mst.repository.PracticeWorkRepository;
import com.mst.security.details.UserDetailsImpl;
import com.mst.service.CourseService;
import com.mst.service.PracticeWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.mst.repository.specification.PracticeWorkSpecification.inCourseByUser;

@RequiredArgsConstructor
@Service
public class PracticeWorkServiceImpl implements PracticeWorkService {

    private final CourseService courseService;

    private final PracticeWorkMapper practiceWorkMapper;

    private final PracticeWorkRepository practiceWorkRepository;

    @Override
    public PageResponse<PracticeWorkResponse> getByCourseId(Long courseId, Integer currentPage, Integer sizePage, String param, String order,
                                                            UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();

        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }
        Specification<PracticeWorkEntity> specification = inCourseByUser(userId, courseId);
        Page<PracticeWorkEntity> practiceWorkEntityPage = practiceWorkRepository.findAll(specification, PageRequest.of(currentPage, sizePage, sort));
        return PageResponse.<PracticeWorkResponse>builder()
                .totalPages(practiceWorkEntityPage.getTotalPages())
                .currentPage(currentPage)
                .totalElements(practiceWorkEntityPage.getTotalElements())
                .responses(practiceWorkMapper.toResponse(practiceWorkEntityPage.getContent()))
                .build();
    }

    @Override
    public PracticeWorkResponse getById(Long workId) {
        return practiceWorkMapper.toResponse(practiceWorkRepository.findById(workId).orElseThrow(PracticeWorkNotFoundException::new));
    }
}
