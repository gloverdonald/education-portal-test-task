package com.mst.service;

import com.mst.dto.response.AcademyGroupResponse;
import com.mst.dto.response.PageResponse;
import com.mst.model.AcademyGroupEntity;

public interface AcademyGroupService {
    AcademyGroupEntity getEntityById(Long id);

    AcademyGroupResponse getAcademyGroupResponse(Long id);

    PageResponse<AcademyGroupResponse> getAllAcademyGroup(Long universityYear, Integer currentPage, Integer sizePage, String param, String order, String searchValue);
}
