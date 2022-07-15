package com.example.cryptotrading.controller;

import com.example.cryptotrading.exceptions.NotEnoughAppResourcesException;
import com.example.cryptotrading.exceptions.NotEnoughUserResourcesException;
import com.example.cryptotrading.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                          @RequestParam Double amountToBuy) throws NotEnoughAppResourcesException, NotEnoughUserResourcesException {
        userService.buyCrypto(currencyName, amountToBuy);
    }

    @PostMapping("/sellCrypto")
    public void sellCrypto(@RequestParam String currencyName,
                          @RequestParam Double amountToSell) throws NotEnoughUserResourcesException {
        userService.sellCrypto(currencyName, amountToSell);
    }
}
