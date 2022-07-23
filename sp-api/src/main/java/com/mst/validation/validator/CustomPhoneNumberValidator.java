package com.mst.validation.validator;

import com.mst.validation.annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

public class CustomPhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private final static String REGEX = "((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}";

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return Objects.isNull(phone) || Pattern.compile(REGEX).matcher(phone).matches();
    }
}