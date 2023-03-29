package com.lucasit.withdraw.request.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasit.withdraw.annotation.ValidPaymentOperationType;
import jakarta.validation.constraints.Min;
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
public class PaymentRequestBody {

    @Min(10)
    @NotNull(message = "The wallet_account_number cannot be null")
    @JsonProperty("wallet_account_number")
    private String walletAccountNumber;

    @Min(9)
    @JsonProperty("external_account_number")
    @NotNull(message = "The external_account_number cannot be null")
    private String externalAccountNumber;

    @ValidPaymentOperationType
    @JsonProperty("operation_type")
    @NotNull(message = "The operation_type cannot be null")
    private Long operationType;

    @NotNull(message = "The amount cannot be null")
    @Positive(message = "The amount should be greater than 0")
    private BigDecimal amount;
}
