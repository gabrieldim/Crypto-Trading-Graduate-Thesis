package com.example.cryptotrading.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String tradedCryptoName;

    private Double amount;

    @OneToOne
    private AvailableAppCrypto availableAppCrypto;
    public Transaction(LocalDate date, String tradedCryptoName, Double amount) {
        this.date = date;
        this.tradedCryptoName = tradedCryptoName;
        this.amount = amount;
    }
}
