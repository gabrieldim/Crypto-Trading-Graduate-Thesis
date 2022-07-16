package com.example.cryptotrading.controller;

import com.example.cryptotrading.exceptions.NotEnoughAppResourcesException;
import com.example.cryptotrading.exceptions.NotEnoughUserResourcesException;
import com.example.cryptotrading.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addMoney")
    public void addUSDMoneyInWallet(@RequestParam Double deposit){
        userService.addUSDMoneyInWallet(deposit);
    }

    @PostMapping("/buyCrypto")
    public void buyCrypto(@RequestParam String currencyName,
                          @RequestParam Double amountToBuy)
            throws NotEnoughAppResourcesException, NotEnoughUserResourcesException, URISyntaxException, JsonProcessingException {
        userService.buyCrypto(currencyName, amountToBuy);
    }

    @PostMapping("/sellCrypto")
    public void sellCrypto(@RequestParam String currencyName,
                          @RequestParam Double amountToSell)
            throws NotEnoughUserResourcesException, URISyntaxException, JsonProcessingException {
        userService.sellCrypto(currencyName, amountToSell);
    }
}
