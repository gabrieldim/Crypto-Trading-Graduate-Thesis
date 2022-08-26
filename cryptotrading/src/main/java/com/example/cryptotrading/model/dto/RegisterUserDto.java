package com.example.cryptotrading.model.dto;

import com.example.cryptotrading.model.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    private String username;
    private String password;
    private String repeatPassword;
    private String firstName;
    private String lastName;
    private Role role;
    private String creditCardNumbers;
}
