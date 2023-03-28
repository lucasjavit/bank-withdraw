package com.lucasit.withdraw.service;

import com.lucasit.withdraw.model.Transactions;
import com.lucasit.withdraw.request.internal.InternalTransactionResponseBody;
import com.lucasit.withdraw.request.internal.PaymentRequestBody;
import com.lucasit.withdraw.request.internal.StatusResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    InternalTransactionResponseBody payment(PaymentRequestBody paymentRequestBody);

    StatusResponseBody getStatus(Long paymentId);

    Page<Transactions> getTransactions(Pageable pageable);
}
