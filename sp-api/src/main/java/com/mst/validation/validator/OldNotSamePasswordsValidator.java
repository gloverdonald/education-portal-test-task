package com.mst.validation.validator;

import com.mst.dto.request.PasswordUpdateRequest;
import com.mst.validation.annotation.OldNotSamePasswords;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class OldNotSamePasswordsValidator implements ConstraintValidator<OldNotSamePasswords, PasswordUpdateRequest> {

    @Override
    public boolean isValid(PasswordUpdateRequest request, ConstraintValidatorContext context) {
        if (Objects.isNull(request.getOldPassword()) || Objects.isNull(request.getNewPassword())) {
            return true;
        }
        return !request.getOldPassword().equals(request.getNewPassword());
    }
}
