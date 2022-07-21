package com.example.cryptotrading.service;

import com.example.cryptotrading.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionByUsername(String user);
}
