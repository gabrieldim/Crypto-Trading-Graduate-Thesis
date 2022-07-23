package com.example.cryptotrading.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellCryptoDto {

    private String currencyName;

    private Double amountToSell;
}
