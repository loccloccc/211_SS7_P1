package com.bank.digital.transaction;

import com.bank.digital.transaction.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S07Bai1Application implements CommandLineRunner  {
    @Autowired
    private TransactionService transactionService;
    public static void main(String[] args) {
        SpringApplication.run(S07Bai1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        transactionService.processPayment("075555",12000);
    }
}
