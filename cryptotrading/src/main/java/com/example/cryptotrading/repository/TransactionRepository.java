package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
