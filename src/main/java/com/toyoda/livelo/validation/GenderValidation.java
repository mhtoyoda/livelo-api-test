package com.toyoda.livelo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface GenderValidation {

    Class<? extends Enum<?>> enumClass();
    String message() default "must be any of M or F";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
