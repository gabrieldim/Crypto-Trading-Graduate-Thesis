package com.example.cryptotrading.controller;

import com.example.cryptotrading.dto.jsonparser.APIResponseCryptocurrencies;
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

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class CryptoApiController {


    private final CryptoHistoryGraphDataService cryptoHistoryGraphDataService;
    public CryptoApiController(CryptoHistoryGraphDataService cryptoHistoryGraphDataService) {
        this.cryptoHistoryGraphDataService = cryptoHistoryGraphDataService;
    }

    @GetMapping("/crypto")
    public APIResponseCryptocurrencies getCrypto() throws URISyntaxException, JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        //https://pro.coinmarketcap.com/account

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-CMC_PRO_API_KEY","d547fd3a-f9a6-4fdd-9dd0-71774b4cdcd5");

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        final String baseUrl = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        URI uri = new URI(baseUrl);

        String JSON = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        APIResponseCryptocurrencies cryptocurrencyList = objectMapper.readValue(JSON, APIResponseCryptocurrencies.class);
        //cryptocurrencyList.getCryptocurrencyList().forEach(System.out::println);

        //zachuvaj go i vo tabelata na istorija
        cryptoHistoryGraphDataService.saveCryptoFromAPI(cryptocurrencyList);

        return cryptocurrencyList;
    }
}
