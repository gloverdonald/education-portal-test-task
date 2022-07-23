package com.mst.validation.annotation;

import com.mst.validation.validator.OldNotSamePasswordsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OldNotSamePasswordsValidator.class)
public @interface OldNotSamePasswords {

    String message() default "New and old password are the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
