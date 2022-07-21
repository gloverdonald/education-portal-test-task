package com.mst.service;

import com.mst.dto.response.PageResponse;
import com.mst.dto.response.UniversityResponse;

public interface UniversityService {

    UniversityResponse getUniversity(Long id);

    PageResponse<UniversityResponse> getAllUniversity(Integer currentPage, Integer sizePage, String param, String order, String searchValue);
}
