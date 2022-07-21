package com.mst.repository;

import com.mst.model.PracticeWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PracticeWorkRepository extends JpaRepository<PracticeWorkEntity, Long>, JpaSpecificationExecutor<PracticeWorkEntity> {
}