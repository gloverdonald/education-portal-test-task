package com.mst.service;

import com.mst.model.SkillEntity;

import java.util.List;
import java.util.Set;

public interface SkillService {
    Set<SkillEntity> getSkillsByTitles(List<String> titles);
}
