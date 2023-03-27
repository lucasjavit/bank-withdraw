package com.lucasit.withdraw.request.external;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalTransactionBalanceResponseBody {

    private BigDecimal balance;
    private String user_id;
}
