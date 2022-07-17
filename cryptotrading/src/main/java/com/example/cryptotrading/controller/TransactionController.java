package com.example.cryptotrading.controller;


import com.example.cryptotrading.model.Transaction;
import com.example.cryptotrading.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(){
        List<Transaction> transactions = transactionService.getAllTransactions();

        transactions.forEach(System.out::println);

        return transactions;
    }

}
