package com.lucasit.withdraw.request.external.payment;


import com.lucasit.withdraw.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountExternalRequestBody {

    private Long accountNumber;
    private String currency;
    private Long routingNumber;

    public static AccountExternalRequestBody getBuild(Account account) {
        return AccountExternalRequestBody.builder()
                .accountNumber(account.getAccountNumber())
                .currency("USD")
                .routingNumber(account.getNationalIdNumber())
                .build();
    }
}
