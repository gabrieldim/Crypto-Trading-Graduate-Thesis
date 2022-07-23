package com.example.cryptotrading.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyCryptoDto {

    private String currencyName;

    private String amountToBuy;
}
