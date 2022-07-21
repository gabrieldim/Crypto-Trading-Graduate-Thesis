package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.Transaction;
import com.example.cryptotrading.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getTransactionByUser(User user);
}
