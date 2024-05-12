package com.vti.blogapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PostIdExistsValidator.class) // This annotation will be validated by PostIdExistsValidator class
@Target(ElementType.PARAMETER) // This annotation can only be applied to method parameters
@Retention(RetentionPolicy.RUNTIME)

    public @interface PostIdExists {
        String message() default "{post.id.Exists.message}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

    }
