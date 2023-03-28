package com.lucasit.withdraw.mapper;

import com.lucasit.withdraw.model.OperationType;
import com.lucasit.withdraw.request.external.payment.response.PaymentResponseBody;
import com.lucasit.withdraw.request.internal.InternalTransactionResponseBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public abstract class WalletMapper {

    public static final WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    public static InternalTransactionResponseBody toTopUpResponseBody(Long userId, BigDecimal newAmount,
                                                                      Long account_id, Long walletTransactionId, BigDecimal balance, OperationType operationType) {
        return InternalTransactionResponseBody.builder()
                .user_id(userId)
                .amount(newAmount)
                .operation_type(operationType.getDescription())
                .account_number(account_id)
                .wallet_transaction_id(walletTransactionId)
                .balance(balance)
                .build();
    }

    public static InternalTransactionResponseBody PaymentResponseBody(Long userId, BigDecimal newAmount, Long account_id, PaymentResponseBody paymentResponseBody, BigDecimal balance, OperationType operationType) {

        return InternalTransactionResponseBody.builder()
                .user_id(userId)
                .amount(newAmount)
                .operation_type(operationType.getDescription())
                .account_number(account_id)
                .wallet_transaction_id(paymentResponseBody == null ? null : paymentResponseBody.getPaymentInfo().getId())
                .balance(balance)
                .build();
    }
}
