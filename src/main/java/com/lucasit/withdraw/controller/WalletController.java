package com.lucasit.withdraw.controller;

import com.lucasit.withdraw.request.external.ExternalTransactionBalanceResponseBody;
import com.lucasit.withdraw.request.internal.InternalTransactionResponseBody;
import com.lucasit.withdraw.request.internal.WalletRequestBody;
import com.lucasit.withdraw.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<InternalTransactionResponseBody> topUp(@RequestBody @Valid WalletRequestBody walletRequestBody) {
        return new ResponseEntity<>(walletService.transaction(walletRequestBody), HttpStatus.CREATED);
    }

    @GetMapping("/balance/{userid}")
    public ResponseEntity<ExternalTransactionBalanceResponseBody> getBalance(@PathVariable Long userid) {
        return new ResponseEntity<>(walletService.getBalance(userid), HttpStatus.OK);
    }
}