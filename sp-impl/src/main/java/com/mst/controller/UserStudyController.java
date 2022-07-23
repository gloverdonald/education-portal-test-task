package com.mst.controller;

import com.mst.api.UserStudyApi;
import com.mst.dto.response.AcademyGroupResponse;
import com.mst.dto.response.PageResponse;
import com.mst.dto.response.UniversityResponse;
import com.mst.dto.response.UniversityYearResponse;
import com.mst.service.AcademyGroupService;
import com.mst.service.UniversityService;
import com.mst.service.UniversityYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserStudyController implements UserStudyApi {

    private final UniversityService universityService;

    private final UniversityYearService universityYearService;

    private final AcademyGroupService academyGroupService;

    @Override
    public ResponseEntity<PageResponse<UniversityResponse>> getAllUniversity(Integer currentPage, Integer sizePage, String param, String order, String searchValue) {
        return ResponseEntity.ok(universityService.getAllUniversity(currentPage, sizePage, param, order, searchValue));
    }

    @Override
    public ResponseEntity<PageResponse<UniversityYearResponse>> getUniversity(Long universityId, Integer currentPage, Integer sizePage, String param, String order) {
        return ResponseEntity.ok(universityYearService.getAllUniversityYearByUniversity(universityId, currentPage, sizePage, param, order));
    }

    @Override
    public ResponseEntity<PageResponse<AcademyGroupResponse>> getAllAcademyGroup(Long universityYear, Integer currentPage, Integer sizePage, String param, String order, String searchValue) {
        return ResponseEntity.ok(academyGroupService.getAllAcademyGroup(universityYear, currentPage, sizePage, param, order, searchValue));
    }
}