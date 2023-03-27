package com.lucasit.withdraw.exception;

import com.lucasit.withdraw.model.Transactions;

public class ExternalException extends RuntimeException {


    private Transactions transactions;

    public ExternalException(String message) {
        super(message);
    }

    public ExternalException(Transactions transactions) {
        this.transactions = transactions;
    }

    public Transactions getTransactions() {
        return transactions;
    }
}
