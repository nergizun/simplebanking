package com.eteration.simplebanking.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransactionTypeValidator.class)
public @interface ValidTransactionType {
    String message() default "Invalid transaction type, valid types are ..";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

