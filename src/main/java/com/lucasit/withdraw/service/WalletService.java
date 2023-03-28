package com.lucasit.withdraw.service;

import com.lucasit.withdraw.request.external.ExternalTransactionBalanceResponseBody;
import com.lucasit.withdraw.request.internal.WalletRequestBody;
import com.lucasit.withdraw.request.internal.InternalTransactionResponseBody;

public interface WalletService {


    InternalTransactionResponseBody transaction(WalletRequestBody walletRequestBody);

    ExternalTransactionBalanceResponseBody getBalance(Long userId);
}
