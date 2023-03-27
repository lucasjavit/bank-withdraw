package com.lucasit.withdraw.request.internal;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountPostBody {

    @NotEmpty(message = "The surname cannot be empty")
    @NotNull(message = "The name cannot be null")
    private String name;

    @NotEmpty(message = "The surname cannot be empty")
    @NotNull(message = "The surname cannot be null")
    private String surname;

    @NotNull(message = "The national_identification cannot be empty")
    @JsonProperty("national_identification")
    private Long nationalIdNumber;

    @NotNull(message = "The account_numer cannot be empty")
    @JsonProperty("account_number")
    private Long accountNumber;

    @NotEmpty(message = "The bank_name cannot be empty")
    @NotNull(message = "The bank_name cannot be null")
    @JsonProperty("bank_name")
    private String bankName;

    @NotNull(message = "The account_type cannot be null")
    @JsonProperty("account_type")
    private Integer accountType;

}
