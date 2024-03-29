package com.example.cryptotrading.controller;

import com.example.cryptotrading.exceptions.InvalidUserCredentialsException;
import com.example.cryptotrading.exceptions.InvalidUserPasswordsException;
import com.example.cryptotrading.exceptions.UserAlreadyExistsException;
import com.example.cryptotrading.model.dto.RegisterUserDto;
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
    public void register(@RequestBody RegisterUserDto registerUserDto)
            throws UserAlreadyExistsException, InvalidUserPasswordsException, InvalidUserCredentialsException {
        System.out.println("test");
        this.userService.register(registerUserDto.getUsername(), registerUserDto.getPassword(), registerUserDto.getRepeatPassword(),
                registerUserDto.getFirstName(), registerUserDto.getLastName(), registerUserDto.getRole(), registerUserDto.getCreditCardNumbers());

    }

}
