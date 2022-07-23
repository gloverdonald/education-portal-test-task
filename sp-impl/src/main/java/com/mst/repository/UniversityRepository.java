package com.mst.repository;

import com.mst.model.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UniversityRepository extends JpaRepository<UniversityEntity, Long>, JpaSpecificationExecutor<UniversityEntity> {

}