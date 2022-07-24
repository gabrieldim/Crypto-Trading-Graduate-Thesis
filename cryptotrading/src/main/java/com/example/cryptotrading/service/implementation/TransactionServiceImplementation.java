package com.example.cryptotrading.service.implementation;

import com.example.cryptotrading.model.Transaction;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.repository.TransactionRepository;
import com.example.cryptotrading.repository.UserRepository;
import com.example.cryptotrading.service.TransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImplementation implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceImplementation(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Transaction> findByOrderByDateDesc() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getAuthorities().toString().equals("[ROLE_ADMIN]")){
            return new ArrayList<>();
        }

        return transactionRepository.findByOrderByDateDesc();
    }

    @Override
    public List<Transaction> getTransactionByUserOrderByDateDesc(String user) {
        User loggedUser = userRepository.findByUsername(user);
        return transactionRepository.getTransactionByUserOrderByDateDesc(loggedUser);
    }

}
