package com.abapayway.sdk.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ReturnDeeplinkValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidReturnDeeplink {
    String message() default "Invalid return_deeplink format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
