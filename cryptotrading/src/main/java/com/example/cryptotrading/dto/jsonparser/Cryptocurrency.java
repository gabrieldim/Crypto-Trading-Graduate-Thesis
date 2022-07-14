package com.example.cryptotrading.dto.jsonparser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cryptocurrency {

    Long id;

    String name;

    Quote quote;

    String symbol;

}
