package com.lucasit.withdraw.mapper;


import com.lucasit.withdraw.model.Account;
import com.lucasit.withdraw.model.OperationType;
import com.lucasit.withdraw.model.Transactions;
import com.lucasit.withdraw.model.TrasactionStatus;
import com.lucasit.withdraw.request.external.ExternalTransactionResponseBody;
import com.lucasit.withdraw.request.external.payment.response.PaymentResponseBody;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper
public abstract class TransactionMapper {

    public static Transactions getBuild(BigDecimal amount,
                                        OperationType operationType,
                                        Account account,
                                        TrasactionStatus trasactionStatus,
                                        ExternalTransactionResponseBody externalTransactionResponseBody,
                                        BigDecimal newAmount) {
        return Transactions.builder()
                .amount(amount.setScale(2))
                .operationType(operationType)
                .eventDate(LocalDateTime.now())
                .account(account)
                .amount(newAmount)
                .transactionId(externalTransactionResponseBody == null ? "" : String.valueOf(externalTransactionResponseBody.getWalletId()))
                .transactionStatus(trasactionStatus)
                .build();
    }

    public static Transactions getBuild(BigDecimal amount,
                                        OperationType operationType,
                                        Account account,
                                        TrasactionStatus trasactionStatus,
                                        PaymentResponseBody paymentResponseBody,
                                        BigDecimal newAmount) {
        return Transactions.builder()
                .amount(amount.setScale(2))
                .operationType(operationType)
                .eventDate(LocalDateTime.now())
                .account(account)
                .amount(newAmount)
                .transactionId(paymentResponseBody == null || paymentResponseBody.getPaymentInfo() == null ? "" : paymentResponseBody.getPaymentInfo().getId())
                .transactionStatus(trasactionStatus)
                .build();
    }
}
