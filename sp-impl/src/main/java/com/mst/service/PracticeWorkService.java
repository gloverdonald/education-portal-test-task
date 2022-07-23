package com.mst.service;

import com.mst.dto.response.PageResponse;
import com.mst.dto.response.PracticeWorkResponse;
import com.mst.security.details.UserDetailsImpl;

public interface PracticeWorkService {
    PageResponse<PracticeWorkResponse> getByCourseId(Long courseId,
                                                     Integer currentPage,
                                                     Integer sizePage,
                                                     String param,
                                                     String order,
                                                     UserDetailsImpl userDetails);

    PracticeWorkResponse getById(Long workId);
}
