package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.CryptoHistoryGraphData;
import com.example.cryptotrading.service.CryptoHistoryGraphDataService;
import org.springframework.web.bind.annotation.*;

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
