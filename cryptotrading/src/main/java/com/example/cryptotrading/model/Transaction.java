package com.example.cryptotrading.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


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

    private LocalDateTime date;

    private String tradedCryptoName;

    private Double amountInUsd;

    private Double amountInCrypto;

    @JsonIgnore
    @ManyToOne
    private User user;

    @OneToOne
    private AvailableAppCrypto availableAppCrypto;
    public Transaction(LocalDateTime date, String tradedCryptoName, Double amountInUsd) {
        this.date = date;
        this.tradedCryptoName = tradedCryptoName;
        this.amountInUsd = amountInUsd;
    }
}
