package com.mst.service.impl;

import com.mst.model.SkillEntity;
import com.mst.repository.SkillRepository;
import com.mst.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public Set<SkillEntity> getSkillsByTitles(List<String> titles) {
        return skillRepository.findByTitleIn(titles);
    }
}
