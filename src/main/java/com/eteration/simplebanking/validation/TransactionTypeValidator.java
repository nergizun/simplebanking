package com.eteration.simplebanking.validation;

import com.eteration.simplebanking.model.TransactionType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class TransactionTypeValidator implements ConstraintValidator<ValidTransactionType, TransactionType> {
    @Override
    public void initialize(ValidTransactionType constraintAnnotation) {
    }

    @Override
    public boolean isValid(TransactionType value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        List<TransactionType> validTypes = Arrays.asList(TransactionType.values());
        return validTypes.contains(value);
    }
}
