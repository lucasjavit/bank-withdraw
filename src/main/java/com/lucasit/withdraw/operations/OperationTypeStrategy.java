package com.lucasit.withdraw.operations;

import java.math.BigDecimal;

public interface OperationTypeStrategy {

    Integer ONE_HUNDRED = 100;

    BigDecimal calculateAmount(BigDecimal balance, BigDecimal amount);

    BigDecimal calculatePercent(Integer percent, BigDecimal value);
}
