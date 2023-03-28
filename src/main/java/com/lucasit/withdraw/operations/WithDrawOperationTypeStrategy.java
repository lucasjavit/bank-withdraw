package com.lucasit.withdraw.operations;

import com.lucasit.withdraw.exception.BusinessException;

import java.math.BigDecimal;

public class WithDrawOperationTypeStrategy implements OperationTypeStrategy {

    @Override
    public BigDecimal calculatePercent(Integer percent, BigDecimal value) {
        if (percent == null && value == null) {
            throw new BusinessException("Percent and value is null");
        }
        BigDecimal newValue = value.multiply(BigDecimal.valueOf((double) percent / ONE_HUNDRED)).setScale(2);

        return value.subtract(newValue);
    }

    @Override
    public BigDecimal calculateAmount(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) <= 0) {
            throw new BusinessException("Amount is less than balance.");
        }
        return balance.subtract(amount);
    }

}
