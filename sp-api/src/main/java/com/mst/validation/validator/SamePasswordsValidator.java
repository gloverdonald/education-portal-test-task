package com.mst.validation.validator;

import com.mst.dto.request.PasswordUpdateRequest;
import com.mst.validation.annotation.SamePasswords;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class SamePasswordsValidator implements ConstraintValidator<SamePasswords, PasswordUpdateRequest> {

    @Override
    public boolean isValid(PasswordUpdateRequest request, ConstraintValidatorContext context) {
        if (Objects.isNull(request.getNewPassword()) || Objects.isNull(request.getRepeatPassword())) {
            return true;
        }
        return request.getNewPassword().equals(request.getRepeatPassword());
    }
}
