package com.example.cryptotrading.service;

import com.example.cryptotrading.dto.jsonparser.APIResponseCryptocurrencies;
import com.example.cryptotrading.model.CryptoHistoryGraphData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CryptoHistoryGraphDataService {

    void saveCryptoFromAPI(APIResponseCryptocurrencies apiResponseCryptocurrencies);

    List<CryptoHistoryGraphData> getAllGraphCryptoData();
}
