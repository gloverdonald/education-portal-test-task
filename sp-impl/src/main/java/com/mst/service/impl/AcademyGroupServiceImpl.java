package com.mst.service.impl;

import com.mst.dto.response.AcademyGroupResponse;
import com.mst.dto.response.PageResponse;
import com.mst.exception.AcademyGroupNotFoundException;
import com.mst.mapper.AcademyGroupMapper;
import com.mst.model.AcademyGroupEntity;
import com.mst.repository.AcademyGroupRepository;
import com.mst.service.AcademyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.mst.repository.specification.AcademyGroupSpecification.byAcademyYear;
import static com.mst.repository.specification.AcademyGroupSpecification.contextSearch;

@RequiredArgsConstructor
@Service
public class AcademyGroupServiceImpl implements AcademyGroupService {

    private final AcademyGroupRepository academyGroupRepository;

    private final AcademyGroupMapper academyGroupMapper;


    @Override
    public AcademyGroupEntity getEntityById(Long id) {
        return academyGroupRepository.findById(id).orElseThrow(AcademyGroupNotFoundException::new);
    }

    @Override
    public AcademyGroupResponse getAcademyGroupResponse(Long id) {
        return academyGroupMapper.toResponse(academyGroupRepository.findById(id).orElseThrow(AcademyGroupNotFoundException::new));
    }

    @Override
    public PageResponse<AcademyGroupResponse> getAllAcademyGroup(Long universityYearId, Integer currentPage, Integer sizePage, String param, String order, String searchValue) {
        Sort sort = Sort.by(Sort.Direction.ASC, param);
        if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, param);
        }
        Specification<AcademyGroupEntity> specification = byAcademyYear(universityYearId).and(contextSearch(searchValue));
        Page<AcademyGroupEntity> academyGroupEntityPage = academyGroupRepository.findAll(specification, PageRequest.of(currentPage, sizePage, sort));
        return PageResponse.<AcademyGroupResponse>builder()
                .currentPage(currentPage)
                .totalPages(academyGroupEntityPage.getTotalPages())
                .totalElements(academyGroupEntityPage.getTotalElements())
                .responses(academyGroupMapper.toResponse(academyGroupEntityPage.getContent()))
                .build();
    }
}
