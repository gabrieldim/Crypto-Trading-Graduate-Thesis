package com.example.cryptotrading.controller;

import com.example.cryptotrading.exceptions.NotEnoughAppResourcesException;
import com.example.cryptotrading.exceptions.NotEnoughUserResourcesException;
import com.example.cryptotrading.model.dto.DepositCashDto;
import com.example.cryptotrading.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addMoney")
    public void addUSDMoneyInWallet(@RequestBody DepositCashDto depositCashDto){
        System.out.println("Cash to deposit: " + depositCashDto.getDeposit());
        userService.addUSDMoneyInWallet(Double.valueOf(depositCashDto.getDeposit()));
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
