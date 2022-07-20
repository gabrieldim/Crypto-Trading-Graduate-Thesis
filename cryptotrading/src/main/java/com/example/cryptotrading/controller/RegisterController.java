package com.example.cryptotrading.controller;

import com.example.cryptotrading.exceptions.InvalidUserCredentialsException;
import com.example.cryptotrading.exceptions.InvalidUserPasswordsException;
import com.example.cryptotrading.exceptions.UserAlreadyExsistsException;
import com.example.cryptotrading.model.enumeration.Role;
import com.example.cryptotrading.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin(value = "*")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam Role role)
            throws UserAlreadyExsistsException, InvalidUserPasswordsException, InvalidUserCredentialsException {
        System.out.println("test");
        this.userService.register(username, password, repeatPassword, firstName, lastName, role);

    }

}
