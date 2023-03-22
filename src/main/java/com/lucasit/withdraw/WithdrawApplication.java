package com.lucasit.withdraw;

import com.lucasit.withdraw.model.OperationType;
import com.lucasit.withdraw.repository.OperationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class WithdrawApplication implements ApplicationRunner {

    private final OperationTypeRepository operationTypeRepository;

    public static void main(String[] args) {
        SpringApplication.run(WithdrawApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        operationTypeRepository.save(new OperationType(1l, "TOP_UP"));
        operationTypeRepository.save(new OperationType(2l, "WITHDRAW"));
        operationTypeRepository.save(new OperationType(3l, "PAYMENT"));
    }
}
