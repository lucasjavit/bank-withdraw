package com.lucasit.withdraw.request.external.payment.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseBody {

    private RequestInfo requestInfo;
    private PaymentInfo paymentInfo;
}
