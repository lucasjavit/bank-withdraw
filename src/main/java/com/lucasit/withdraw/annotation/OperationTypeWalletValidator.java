package com.lucasit.withdraw.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class OperationTypeWalletValidator implements ConstraintValidator<ValidWalletOperationType, Long> {
    @Override
    public boolean isValid(Long currentOperation, ConstraintValidatorContext constraintValidatorContext) {
        List list = Arrays.asList(1l, 2l);

        return list.contains(currentOperation);
    }
}
