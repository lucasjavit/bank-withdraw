package com.lucasit.withdraw.controller;

import com.lucasit.withdraw.model.Transactions;
import com.lucasit.withdraw.request.internal.InternalTransactionResponseBody;
import com.lucasit.withdraw.request.internal.PaymentRequestBody;
import com.lucasit.withdraw.request.internal.StatusResponseBody;
import com.lucasit.withdraw.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<InternalTransactionResponseBody> payment(@RequestBody @Valid PaymentRequestBody paymentRequestBody) {
        return new ResponseEntity<>(paymentService.payment(paymentRequestBody), HttpStatus.CREATED);
    }

    @GetMapping("/{paymentid}")
    public ResponseEntity<StatusResponseBody> getStatus(@PathVariable("paymentid") Long paymentId) {
        return new ResponseEntity<>(paymentService.getStatus(paymentId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Transactions>> getTransactions(Pageable pageable) {
        return new ResponseEntity<>(paymentService.getTransactions(pageable), HttpStatus.OK);
    }


}