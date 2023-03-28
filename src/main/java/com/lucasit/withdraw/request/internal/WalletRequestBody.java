package com.lucasit.withdraw.request.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequestBody {

    @NotNull(message = "The wallet_account_number cannot be null")
    @JsonProperty("wallet_account_number")
    private String walletAccountNumber;

    @NotNull(message = "The operation_type cannot be null")
    @JsonProperty("operation_type")
    private Long operationType;

    @NotNull(message = "The amount cannot be null")
    private BigDecimal amount;

}
