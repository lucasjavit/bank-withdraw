package com.lucasit.withdraw.request.external.payment;

import com.lucasit.withdraw.model.Account;
import com.lucasit.withdraw.request.internal.PaymentRequestBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentExternalRequestBody {

    private SourceWalletRequestBody source;
    private DestinationExternalRequestBody destination;
    private BigDecimal amount;


    public static PaymentExternalRequestBody getBuild(Account walletAccount, Account destinationAccount, PaymentRequestBody paymentRequestBody) {

        var source =
                SourceWalletRequestBody.builder()
                        .type("COMPANY")
                        .sourceInformation(SourceInformationExternalRequestBody.getBuild("ONTOP INC"))
                        .account(AccountExternalRequestBody.getBuild(walletAccount))
                        .build();

        var destination =
                DestinationExternalRequestBody.builder()
                        .name("TONY STARK")
                        .account(AccountExternalRequestBody.getBuild(destinationAccount))
                        .build();

        return PaymentExternalRequestBody.builder()
                .source(source)
                .destination(destination)
                .amount(paymentRequestBody.getAmount())
                .build();
    }

}
