package com.lucasit.withdraw.request.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalTransactionResponseBody {

    private Long user_id;
    private BigDecimal amount;
    private String operation_type;
    private String account_number;
    private String wallet_transaction_id;
    private BigDecimal balance;

}
