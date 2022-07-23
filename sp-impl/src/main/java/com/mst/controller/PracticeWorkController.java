package com.mst.controller;

import com.mst.api.PracticeWorkApi;
import com.mst.dto.response.PageResponse;
import com.mst.dto.response.PracticeWorkResponse;
import com.mst.security.details.UserDetailsImpl;
import com.mst.service.PracticeWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PracticeWorkController implements PracticeWorkApi<UserDetailsImpl> {

    private final PracticeWorkService practiceWorkService;

    @Override
    public ResponseEntity<PageResponse<PracticeWorkResponse>> getUserPracticeWorkOnCourse(Long courseId,
                                                                                          Integer currentPage,
                                                                                          Integer sizePage,
                                                                                          String param,
                                                                                          String order,
                                                                                          UserDetailsImpl userDetails) {
        return ResponseEntity.ok(practiceWorkService.getByCourseId(courseId, currentPage, sizePage, param, order, userDetails));
    }

    @Override
    public ResponseEntity<PracticeWorkResponse> getPracticeWork(Long workId) {
        return ResponseEntity.ok(practiceWorkService.getById(workId));
    }
}
