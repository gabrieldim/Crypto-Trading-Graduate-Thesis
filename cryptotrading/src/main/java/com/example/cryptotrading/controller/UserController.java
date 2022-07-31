package com.example.cryptotrading.controller;

import com.example.cryptotrading.exceptions.InvalidCryptocurrencySearchException;
import com.example.cryptotrading.exceptions.NotEnoughAppResourcesException;
import com.example.cryptotrading.exceptions.NotEnoughUserResourcesException;
import com.example.cryptotrading.model.CryptoInWallet;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.model.dto.BuyCryptoDto;
import com.example.cryptotrading.model.dto.DepositCashDto;
import com.example.cryptotrading.model.dto.SellCryptoDto;
import com.example.cryptotrading.repository.UserRepository;
import com.example.cryptotrading.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/addMoney")
    public void addUSDMoneyInWallet(@RequestBody DepositCashDto depositCashDto){
        System.out.println("Cash to deposit: " + depositCashDto.getDeposit());
        userService.addUSDMoneyInWallet(Double.valueOf(depositCashDto.getDeposit()));
    }

    @PostMapping("/buyCrypto")
    public void buyCrypto(@RequestBody BuyCryptoDto buyCryptoDto)
            throws NotEnoughAppResourcesException, NotEnoughUserResourcesException, URISyntaxException, JsonProcessingException, InvalidCryptocurrencySearchException {
        System.out.println("Crypto to buy: " + buyCryptoDto.getCurrencyName() + " - " + buyCryptoDto.getAmountToBuy());
        userService.buyCrypto(buyCryptoDto.getCurrencyName(), Double.valueOf(buyCryptoDto.getAmountToBuy()));
    }

    @PostMapping("/sellCrypto")
    public void sellCrypto(@RequestBody SellCryptoDto sellCryptoDto)
            throws NotEnoughUserResourcesException, URISyntaxException, JsonProcessingException, InvalidCryptocurrencySearchException {
        System.out.println("Crypto to sell: " + sellCryptoDto.getCurrencyName() + " - " + sellCryptoDto.getAmountToSell());
        userService.sellCrypto(sellCryptoDto.getCurrencyName(), sellCryptoDto.getAmountToSell());
    }

    @GetMapping("/getLoggedUserCryptocurrencies")
    public List<CryptoInWallet> getLoggedUserCryptocurrencies(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserUsername = auth.getPrincipal().toString();
        User user = userRepository.findByUsername(loggedUserUsername);
        return user.getCryptoInWallet();
    }

    @GetMapping("/loggedUser")
    public ResponseEntity<?> getLoggedUserUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserUsername = auth.getPrincipal().toString();
        User user = userRepository.findByUsername(loggedUserUsername);
        return new ResponseEntity<>(
                user.getUsername(),
                HttpStatus.OK);
    }

    @GetMapping("/availableResources")
    public String getLoggedUserAvailableResources(){
        return userService.getLoggedUserAvailableResource().toString();
    }

}
