package com.transactionsexample.spring_transactions.controller;

import com.transactionsexample.spring_transactions.dto.TransactionDTO;
import com.transactionsexample.spring_transactions.repository.TransactionRepository;
import com.transactionsexample.spring_transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(id);
        if (transactionDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>("Account successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
