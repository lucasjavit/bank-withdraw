package com.lucasit.withdraw.request.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasit.withdraw.annotation.ValidWalletOperationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @ValidWalletOperationType
    @NotNull(message = "The operation_type cannot be null")
    @JsonProperty("operation_type")
    private Long operationType;

    @Positive(message = "The amount should be greater than 0")
    @NotNull(message = "The amount cannot be null")
    private BigDecimal amount;

}
