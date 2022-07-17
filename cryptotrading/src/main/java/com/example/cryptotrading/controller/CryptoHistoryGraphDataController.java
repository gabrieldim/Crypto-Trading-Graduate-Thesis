package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.CryptoHistoryGraphData;
import com.example.cryptotrading.service.CryptoHistoryGraphDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CryptoHistoryGraphDataController{

    CryptoHistoryGraphDataService cryptoHistoryGraphDataService;

    @GetMapping("/graphData")
    public List<CryptoHistoryGraphData> getGraphData(){
        return cryptoHistoryGraphDataService.getAllGraphCryptoData();
    }

}
