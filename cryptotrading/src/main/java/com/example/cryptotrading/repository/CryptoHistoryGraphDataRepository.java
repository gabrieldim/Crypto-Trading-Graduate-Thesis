package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.CryptoHistoryGraphData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CryptoHistoryGraphDataRepository extends JpaRepository<CryptoHistoryGraphData, Long> {

    List<CryptoHistoryGraphData> findByOrderByTimeOfThisRecordDesc();

}
