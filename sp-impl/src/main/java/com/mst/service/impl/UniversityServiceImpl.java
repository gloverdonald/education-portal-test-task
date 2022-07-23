package com.mst.service.impl;

import com.mst.dto.response.PageResponse;
import com.mst.dto.response.UniversityResponse;
import com.mst.exception.UniversityNotFoundException;
import com.mst.mapper.UniversityMapper;
import com.mst.model.UniversityEntity;
import com.mst.repository.UniversityRepository;
import com.mst.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.mst.repository.specification.UniversitySpecification.contextSearch;

@RequiredArgsConstructor
@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    private final UniversityMapper universityMapper;

    @Override
    public UniversityResponse getUniversity(Long id) {
        return universityMapper.toResponse(universityRepository.findById(id).orElseThrow(UniversityNotFoundException::new));
    }

    @Override
    public PageResponse<UniversityResponse> getAllUniversity(Integer currentPage, Integer sizePage, String param, String order, String searchValue) {
        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }
        Specification<UniversityEntity> specification = contextSearch(searchValue);
        Page<UniversityEntity> universityEntityPage = universityRepository.findAll(specification, PageRequest.of(currentPage, sizePage, sort));
        return PageResponse.<UniversityResponse>builder()
                .currentPage(currentPage)
                .totalPages(universityEntityPage.getTotalPages())
                .totalElements(universityEntityPage.getTotalElements())
                .responses(universityMapper.toResponse(universityEntityPage.getContent()))
                .build();
    }
}