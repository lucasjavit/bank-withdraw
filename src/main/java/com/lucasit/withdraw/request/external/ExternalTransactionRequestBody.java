package com.lucasit.withdraw.request.external;

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
public class ExternalTransactionRequestBody {

    @JsonProperty("user_id")
    @NotNull(message = "The user_id cannot be null")
    private Long userId;

    @NotNull(message = "The amount cannot be null")
    private BigDecimal amount;
}
