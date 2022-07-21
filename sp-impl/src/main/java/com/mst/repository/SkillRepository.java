package com.mst.repository;

import com.mst.model.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    Optional<SkillEntity> findSkillByTitle(String title);

    Set<SkillEntity> findByTitleIn(List<String> names);
}
