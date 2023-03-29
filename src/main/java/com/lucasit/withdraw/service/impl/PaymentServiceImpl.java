package com.lucasit.withdraw.service.impl;

import com.lucasit.withdraw.exception.BusinessException;
import com.lucasit.withdraw.exception.ExternalException;
import com.lucasit.withdraw.mapper.TransactionMapper;
import com.lucasit.withdraw.mapper.WalletMapper;
import com.lucasit.withdraw.model.Account;
import com.lucasit.withdraw.model.OperationType;
import com.lucasit.withdraw.model.Transactions;
import com.lucasit.withdraw.model.TrasactionStatus;
import com.lucasit.withdraw.operations.OperationTypeStrategy;
import com.lucasit.withdraw.repository.AccountRepository;
import com.lucasit.withdraw.repository.OperationTypeRepository;
import com.lucasit.withdraw.repository.TransactionRepository;
import com.lucasit.withdraw.request.external.ExternalError;
import com.lucasit.withdraw.request.external.payment.PaymentExternalRequestBody;
import com.lucasit.withdraw.request.external.payment.response.PaymentResponseBody;
import com.lucasit.withdraw.request.internal.InternalTransactionResponseBody;
import com.lucasit.withdraw.request.internal.PaymentRequestBody;
import com.lucasit.withdraw.request.internal.StatusResponseBody;
import com.lucasit.withdraw.rest.RestCaller;
import com.lucasit.withdraw.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final Integer TEN_PERCENT = 10;

    @Value("${uri.payment}")
    private String uriTransaction;

    @Value("${uri.payment.status}")
    private String uriStatus;


    private final TransactionRepository transactionRepository;
    private final OperationTypeRepository operationTypeRepository;
    private final AccountRepository accountRepository;
    private final RestCaller restCaller;


    @Transactional
    public InternalTransactionResponseBody payment(PaymentRequestBody paymentRequestBody) {

        var operationType = operationTypeRepository.findById(paymentRequestBody.getOperationType())
                .orElseThrow(() -> new BusinessException("Operation type not found"));

        OperationTypeStrategy operationTypeStrategy = OperationType.OperationType(operationType.getId());


        Account walletAccount = accountRepository.findAccountByAccountNumber(paymentRequestBody.getWalletAccountNumber())
                .orElseThrow(() -> new BusinessException("Wallet Account not found"));

        Account externalAccount = accountRepository.findAccountByAccountNumber(paymentRequestBody.getExternalAccountNumber())
                .orElseThrow(() -> new BusinessException("External Account not found"));

        BigDecimal newAmount = operationTypeStrategy.calculatePercent(TEN_PERCENT, paymentRequestBody.getAmount());

        walletAccount.setBalance(operationTypeStrategy.calculateAmount(walletAccount.getBalance(), newAmount));


        PaymentExternalRequestBody paymentExternalRequestBody = PaymentExternalRequestBody
                .getBuild(walletAccount, externalAccount, paymentRequestBody);


        try {
            PaymentResponseBody paymentResponseBody = callPost(paymentExternalRequestBody);

            /*Update account*/

            accountRepository.save(walletAccount);

            /*Save - Transaction*/

            Transactions transactions = TransactionMapper.getBuild(newAmount, operationType, walletAccount, TrasactionStatus.PROGRESS, paymentResponseBody, newAmount);


            transactionRepository.save(transactions);

            return WalletMapper.PaymentResponseBody(walletAccount.getUser().getId(), newAmount,
                    String.valueOf(walletAccount.getId()), paymentResponseBody, walletAccount.getBalance(), operationType);

        } catch (HttpClientErrorException ex) {
            ExternalError externalError = ex.getResponseBodyAs(ExternalError.class);

            throw new ExternalException(TransactionMapper.getBuild(newAmount, operationType, walletAccount, TrasactionStatus.FAILED, new PaymentResponseBody(), newAmount));
        }
    }

    public StatusResponseBody getStatus(Long paymentId) {

        ResponseEntity<StatusResponseBody> response = restCaller
                .callGet(uriTransaction + paymentId + uriStatus, new ParameterizedTypeReference<>() {
                });

        return response != null && response.hasBody() ? response.getBody() : new StatusResponseBody();
    }

    public Page<Transactions> getTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    private PaymentResponseBody callPost(PaymentExternalRequestBody paymentExternalRequestBody) throws RestClientException {

        ResponseEntity<PaymentResponseBody> response = restCaller
                .callPost(uriTransaction, paymentExternalRequestBody, new ParameterizedTypeReference<>() {
                });

        return response != null && response.hasBody() ? response.getBody() : new PaymentResponseBody();
    }


}