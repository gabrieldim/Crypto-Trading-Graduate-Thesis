package com.example.cryptotrading.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crypto_in_wallet")
public class CryptoInWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyHeldName;

    private Double currencyHeldAmount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public CryptoInWallet(String currencyHeldName, Double currencyHeldAmount) {
        this.currencyHeldName = currencyHeldName;
        this.currencyHeldAmount = currencyHeldAmount;
    }
}
