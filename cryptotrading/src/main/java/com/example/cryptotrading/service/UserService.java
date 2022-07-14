package com.example.cryptotrading.service;

import com.example.cryptotrading.exceptions.InvalidUserCredentialsException;
import com.example.cryptotrading.exceptions.InvalidUserPasswordsException;
import com.example.cryptotrading.exceptions.UserAlreadyExsistsException;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.model.enumeration.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String firstName, String lastName, Role role) throws InvalidUserCredentialsException, InvalidUserPasswordsException, UserAlreadyExsistsException;


}
