package com.lucasit.withdraw.request.external.payment.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseBody {

    private RequestInfo requestInfo;
    private PaymentInfo paymentInfo;
}