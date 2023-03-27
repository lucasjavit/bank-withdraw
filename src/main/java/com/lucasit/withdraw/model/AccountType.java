package com.lucasit.withdraw.model;

import java.util.Arrays;

public enum AccountType {


    WALLET_ACCOUNT(1), EXTERNAL_ACCOUNT(2);

    int accountid;

    AccountType(int i) {
        this.accountid = i;
    }

    public static AccountType getEnum(int index) {
        return Arrays.stream(AccountType.values()).filter(it -> it.accountid == index).findFirst().get();
    }

}
