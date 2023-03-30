package com.lucasit.withdraw.exception;

import com.lucasit.withdraw.model.Transactions;
import org.springframework.web.client.RestClientException;

public class ExternalException extends RestClientException {


    private Transactions transactions;

    public ExternalException(String msg, Transactions transactions) {
        super(msg);
        this.transactions = transactions;
    }

    public Transactions getTransactions() {
        return transactions;
    }
}
