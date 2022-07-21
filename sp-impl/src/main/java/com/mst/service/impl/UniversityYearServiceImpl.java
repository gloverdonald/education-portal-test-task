package com.mst.service.impl;

import com.mst.dto.response.PageResponse;
import com.mst.dto.response.UniversityYearResponse;
import com.mst.mapper.UniversityYearMapper;
import com.mst.model.UniversityYearEntity;
import com.mst.repository.UniversityYearRepository;
import com.mst.service.UniversityYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UniversityYearServiceImpl implements UniversityYearService {

    private final UniversityYearRepository universityYearRepository;

    private final UniversityYearMapper universityYearMapper;

    @Override
    public PageResponse<UniversityYearResponse> getAllUniversityYearByUniversity(Long universityId, Integer currentPage, Integer sizePage, String param, String order) {
        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }
        Page<UniversityYearEntity> universityYearEntityPage = universityYearRepository.findAllByUniversityId(universityId, PageRequest.of(currentPage, sizePage, sort));
        return PageResponse.<UniversityYearResponse>builder()
                .currentPage(currentPage)
                .totalPages(universityYearEntityPage.getTotalPages())
                .totalElements(universityYearEntityPage.getTotalElements())
                .responses(universityYearMapper.toResponse(universityYearEntityPage.getContent()))
                .build();
    }
}
