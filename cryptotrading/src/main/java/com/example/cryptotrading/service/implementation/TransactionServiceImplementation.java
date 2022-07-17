package com.example.cryptotrading.service.implementation;

import com.example.cryptotrading.model.Transaction;
import com.example.cryptotrading.model.enumeration.Role;
import com.example.cryptotrading.repository.TransactionRepository;
import com.example.cryptotrading.service.TransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImplementation implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImplementation(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getAuthorities().toString().equals("[ROLE_ADMIN]")){
            return new ArrayList<>();
        }

        return transactionRepository.findAll();
    }
}