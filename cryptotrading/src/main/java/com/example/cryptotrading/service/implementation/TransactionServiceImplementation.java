package com.example.cryptotrading.service.implementation;

import com.example.cryptotrading.model.Transaction;
import com.example.cryptotrading.repository.TransactionRepository;
import com.example.cryptotrading.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImplementation implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImplementation(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
