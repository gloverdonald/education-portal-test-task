package com.mst.service;

import com.mst.dto.response.PageResponse;
import com.mst.dto.response.UniversityYearResponse;

public interface UniversityYearService {
    PageResponse<UniversityYearResponse> getAllUniversityYearByUniversity(Long universityId, Integer currentPage, Integer sizePage, String param, String order);
}
