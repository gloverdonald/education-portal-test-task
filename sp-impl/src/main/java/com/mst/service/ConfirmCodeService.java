package com.mst.service;

import com.mst.model.ConfirmCodeEntity;

public interface ConfirmCodeService {
    ConfirmCodeEntity getEntityByConfirmCode(String code);
}
