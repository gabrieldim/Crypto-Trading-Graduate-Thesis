package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.AvailableAppCrypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableAppCryptoRepository extends JpaRepository<AvailableAppCrypto, Long> {
}
