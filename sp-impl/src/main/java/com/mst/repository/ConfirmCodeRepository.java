package com.mst.repository;

import com.mst.model.ConfirmCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmCodeRepository extends JpaRepository<ConfirmCodeEntity, Long> {

    Optional<ConfirmCodeEntity> findByConfirmCode(String confirmCode);
}
