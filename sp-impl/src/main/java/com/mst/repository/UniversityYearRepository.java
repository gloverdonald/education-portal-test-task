package com.mst.repository;

import com.mst.model.UniversityYearEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityYearRepository extends JpaRepository<UniversityYearEntity, Long> {
    Page<UniversityYearEntity> findAllByUniversityId(Long id, Pageable pageable);
}
