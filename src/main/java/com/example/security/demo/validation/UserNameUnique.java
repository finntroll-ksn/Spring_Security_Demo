package com.example.security.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameUniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameUnique {
    String message() default "Fail -> Username is already taken! 123333";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
