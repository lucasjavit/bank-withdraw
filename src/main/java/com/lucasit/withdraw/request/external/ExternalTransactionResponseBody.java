package com.lucasit.withdraw.request.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalTransactionResponseBody {

    @JsonProperty("wallet_transaction_id")
    private Long walletId;

    private BigDecimal amount;

    private Long user_id;


}
