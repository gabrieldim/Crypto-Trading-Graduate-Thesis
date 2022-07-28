package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.CryptoHistoryGraphData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CryptoHistoryGraphDataRepository extends JpaRepository<CryptoHistoryGraphData, Long> {

    List<CryptoHistoryGraphData> findByOrderByTimeOfThisRecordDesc();

    @Query(value = "select distinct chdp.symbol from crypto_history_graph_data as chdp",nativeQuery = true)
    List<String> findDistinctBySymbol();
}
