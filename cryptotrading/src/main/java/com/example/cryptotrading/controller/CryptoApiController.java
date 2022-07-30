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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class CryptoApiController {


    private final CryptoHistoryGraphDataService cryptoHistoryGraphDataService;
    public CryptoApiController(CryptoHistoryGraphDataService cryptoHistoryGraphDataService) {
        this.cryptoHistoryGraphDataService = cryptoHistoryGraphDataService;
    }

    @GetMapping("/crypto")
    public List<CryptoHistoryGraphData> getCrypto(@RequestParam String cryptoSymbol){
        System.out.println("Symbol to filter: " + cryptoSymbol);
        List<CryptoHistoryGraphData> cryptoHistoryGraphData = cryptoHistoryGraphDataService.getHistoricalCryptoData()
                .stream().filter(x -> x.getSymbol().equals(cryptoSymbol)).limit(13)
                .collect(Collectors.toList());

        Collections.reverse(cryptoHistoryGraphData);

        return cryptoHistoryGraphData;
    }

    @GetMapping("/cryptoSymbolName")
    public List<String> getAllCryptoSymbolNames(){
        return cryptoHistoryGraphDataService.getAllCryptoSymbolNames();
    }

}
