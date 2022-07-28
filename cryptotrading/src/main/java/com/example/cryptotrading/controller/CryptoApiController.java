package com.example.cryptotrading.controller;

import com.example.cryptotrading.dto.jsonparser.APIResponseCryptocurrencies;
import com.example.cryptotrading.model.CryptoHistoryGraphData;
import com.example.cryptotrading.service.CryptoHistoryGraphDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class CryptoApiController {


    private final CryptoHistoryGraphDataService cryptoHistoryGraphDataService;
    public CryptoApiController(CryptoHistoryGraphDataService cryptoHistoryGraphDataService) {
        this.cryptoHistoryGraphDataService = cryptoHistoryGraphDataService;
    }

    @GetMapping("/crypto")
    public List<CryptoHistoryGraphData> getCrypto(){

        return cryptoHistoryGraphDataService.getHistoricalCryptoData();
    }

    @GetMapping("/cryptoSymbolName")
    public List<String> getAllCryptoSymbolNames(){
        return cryptoHistoryGraphDataService.getAllCryptoSymbolNames();
    }

}
