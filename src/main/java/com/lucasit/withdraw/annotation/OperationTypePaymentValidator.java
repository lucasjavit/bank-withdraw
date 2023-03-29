package com.lucasit.withdraw.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class OperationTypePaymentValidator implements ConstraintValidator<ValidPaymentOperationType, Long> {
    @Override
    public boolean isValid(Long currentOperation, ConstraintValidatorContext constraintValidatorContext) {
        List list = Arrays.asList(3l);

        return list.contains(currentOperation);
    }
}
