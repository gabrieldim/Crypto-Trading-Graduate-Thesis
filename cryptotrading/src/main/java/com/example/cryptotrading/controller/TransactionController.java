package com.example.cryptotrading.controller;


import com.example.cryptotrading.model.Transaction;
import com.example.cryptotrading.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Method that retrieve all transactions in this application.
     *
     * @return
     */
    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransactions(){
        List<Transaction> transactions = transactionService.getAllTransactions();

        if(transactions.size()==0){
            return new ResponseEntity<>(
                    "Sorry, currently you don't have permissions for this action or there are no transactions!",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }

}
