package com.lucasit.withdraw.mapper;

import com.lucasit.withdraw.model.Account;
import com.lucasit.withdraw.model.AccountType;
import com.lucasit.withdraw.request.internal.AccountPostBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    public Account toEntity(AccountPostBody accountPostBody) {
        if (accountPostBody == null) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.nationalIdNumber(accountPostBody.getNationalIdNumber());
        account.accountNumber(accountPostBody.getAccountNumber());
        account.bankName(accountPostBody.getBankName());
        account.balance(BigDecimal.ZERO.setScale(2));
        account.accountType(AccountType.getEnum(accountPostBody.getAccountType()));

        return account.build();
    }


}
