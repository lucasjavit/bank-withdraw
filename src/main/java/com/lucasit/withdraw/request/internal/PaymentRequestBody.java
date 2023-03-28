package com.lucasit.withdraw.request.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
public class PaymentRequestBody {

    @NotNull(message = "The wallet_account_number cannot be null")
    @JsonProperty("wallet_account_number")
    @Min(10)
    private String walletAccountNumber;

    @NotNull(message = "The external_account_number cannot be null")
    @JsonProperty("external_account_number")
    @Min(10)
    private String externalAccountNumber;

    @NotNull(message = "The operation_type cannot be null")
    @JsonProperty("operation_type")
    private Long operationType;

    @NotNull(message = "The amount cannot be null")
    private BigDecimal amount;
}
