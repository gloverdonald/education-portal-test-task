package com.mst.service.impl;

import com.mst.exception.ConfirmCodeNotFoundException;
import com.mst.model.ConfirmCodeEntity;
import com.mst.repository.ConfirmCodeRepository;
import com.mst.service.ConfirmCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConfirmCodeServiceImpl implements ConfirmCodeService {
    private final ConfirmCodeRepository confirmCodeRepository;

    @Override
    public ConfirmCodeEntity getEntityByConfirmCode(String code) {
        return confirmCodeRepository.findByConfirmCode(code).orElseThrow(ConfirmCodeNotFoundException::new);
    }
}
