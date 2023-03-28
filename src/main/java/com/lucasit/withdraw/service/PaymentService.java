package com.lucasit.withdraw.service;

import com.lucasit.withdraw.request.internal.InternalTransactionResponseBody;
import com.lucasit.withdraw.request.internal.PaymentRequestBody;
import com.lucasit.withdraw.request.internal.StatusResponseBody;

public interface PaymentService {

    InternalTransactionResponseBody payment(PaymentRequestBody paymentRequestBody);

    StatusResponseBody getStatus(Long paymentId);
}
