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

    private String accountNumber;
    private String currency;
    private String routingNumber;

    public static AccountExternalRequestBody getBuild(Account account) {
        return AccountExternalRequestBody.builder()
                .accountNumber(account.getAccountNumber())
                .currency("USD")
                .routingNumber(account.getNationalIdNumber())
                .build();
    }
}
