package com.example.cryptotrading.service.implementation;

import com.example.cryptotrading.dto.jsonparser.APIResponseCryptocurrencies;
import com.example.cryptotrading.dto.jsonparser.Cryptocurrency;
import com.example.cryptotrading.model.CryptoHistoryGraphData;
import com.example.cryptotrading.repository.CryptoHistoryGraphDataRepository;
import com.example.cryptotrading.service.CryptoHistoryGraphDataService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CryptoHistoryGraphDataServiceImplementation implements CryptoHistoryGraphDataService {

    private final CryptoHistoryGraphDataRepository cryptoHistoryGraphDataRepository;

    public CryptoHistoryGraphDataServiceImplementation(CryptoHistoryGraphDataRepository cryptoHistoryGraphDataRepository) {
        this.cryptoHistoryGraphDataRepository = cryptoHistoryGraphDataRepository;
    }

    @Override
    public void saveCryptoFromAPI(APIResponseCryptocurrencies apiResponseCryptocurrencies) {

        List<Cryptocurrency> cryptocurrencyList = apiResponseCryptocurrencies.getCryptocurrencyList();

        for(int i=0; i<cryptocurrencyList.size(); i++){
            CryptoHistoryGraphData cryptoHistoryGraphData = new CryptoHistoryGraphData();
            cryptoHistoryGraphData.setName(cryptocurrencyList.get(i).getName());
            cryptoHistoryGraphData.setPrice(cryptocurrencyList.get(i).getQuote().getUsd().getPrice());
            cryptoHistoryGraphData.setMarketCap(cryptocurrencyList.get(i).getQuote().getUsd().getMarketCap());
            cryptoHistoryGraphData.setSymbol(cryptocurrencyList.get(i).getSymbol());
            cryptoHistoryGraphData.setTimeOfThisRecord(LocalDateTime.now());
            cryptoHistoryGraphDataRepository.save(cryptoHistoryGraphData);
        }


    }

    @Override
    public List<CryptoHistoryGraphData> getAllGraphCryptoData() {
        return cryptoHistoryGraphDataRepository.findAll();
    }
}
