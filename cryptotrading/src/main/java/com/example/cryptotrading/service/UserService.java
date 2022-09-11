package com.example.cryptotrading.service;

import com.example.cryptotrading.exceptions.*;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.model.enumeration.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String firstName, String lastName, Role role, String creditCardNumbers) throws InvalidUserCredentialsException, InvalidUserPasswordsException, UserAlreadyExistsException;

    void addUSDMoneyInWallet(Double deposit);

    void buyCrypto(String currencyName, Double amountToBuy) throws NotEnoughAppResourcesException, NotEnoughUserResourcesException, URISyntaxException, JsonProcessingException, InvalidCryptocurrencySearchException;

    void sellCrypto(String currencyName, Double amountToSell) throws NotEnoughUserResourcesException, URISyntaxException, JsonProcessingException, InvalidCryptocurrencySearchException;

    void withdrawAmount(Integer amountToWithdraw, String username) throws NotEnoughUserResourcesException;

    Double getLoggedUserAvailableResource();
}
