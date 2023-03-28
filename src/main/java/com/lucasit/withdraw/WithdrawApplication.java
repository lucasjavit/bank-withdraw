package com.lucasit.withdraw;

import com.lucasit.withdraw.mapper.AccountMapper;
import com.lucasit.withdraw.model.Account;
import com.lucasit.withdraw.model.OperationType;
import com.lucasit.withdraw.model.User;
import com.lucasit.withdraw.repository.AccountRepository;
import com.lucasit.withdraw.repository.OperationTypeRepository;
import com.lucasit.withdraw.repository.UserRepository;
import com.lucasit.withdraw.request.internal.AccountPostBody;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
@RequiredArgsConstructor
public class WithdrawApplication implements ApplicationRunner {

    private final OperationTypeRepository operationTypeRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(WithdrawApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        operationTypeRepository.save(new OperationType(1l, "TOP_UP"));
        operationTypeRepository.save(new OperationType(2l, "WITHDRAW"));
        operationTypeRepository.save(new OperationType(3l, "PAYMENT"));


        AccountPostBody walletAccountPostBody = AccountPostBody.builder()
                .name("Lucho")
                .surname("Rodriguez")
                .nationalIdNumber("028444018")
                .accountNumber("0245253419")
                .bankName("City Bank")
                .accountType(1)
                .build();


        AccountPostBody externalAccountPostBody = AccountPostBody.builder()
                .name("TONY")
                .surname("STARK")
                .nationalIdNumber("211927207")
                .accountNumber("1885226711")
                .bankName("City Bank")
                .accountType(2)
                .build();

        User user = User.builder()
                .firstName(walletAccountPostBody.getName())
                .lastName(walletAccountPostBody.getSurname())
                .build();

        User userSave = userRepository.save(user);

        Account walletAccount = AccountMapper.INSTANCE.toEntity(walletAccountPostBody);
        walletAccount.setUser(userSave);

        Account externalAccount = AccountMapper.INSTANCE.toEntity(externalAccountPostBody);
        externalAccount.setUser(userSave);


        userSave.setAccounts(new ArrayList<>());
        user.getAccounts().add(walletAccount);
        user.getAccounts().add(externalAccount);

        accountRepository.save(walletAccount);
        accountRepository.save(externalAccount);

    }
}
