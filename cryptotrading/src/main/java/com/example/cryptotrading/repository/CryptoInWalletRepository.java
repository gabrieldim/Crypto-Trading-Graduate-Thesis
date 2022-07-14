package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.CryptoInWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoInWalletRepository extends JpaRepository<CryptoInWallet, Long> {
}
