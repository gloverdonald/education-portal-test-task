package com.mst.repository;

import com.mst.model.AcademyGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AcademyGroupRepository extends JpaRepository<AcademyGroupEntity, Long>, JpaSpecificationExecutor<AcademyGroupEntity> {
}