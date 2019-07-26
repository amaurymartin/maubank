package com.maubank.services.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.maubank.utils.Messages;

@Constraint(validatedBy = CategoryValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryValid {

	String message() default Messages.VALIDATION_ERROR;
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
