package com.transactionsexample.spring_transactions.controller;

import com.transactionsexample.spring_transactions.dto.AccountRequestDTO;
import com.transactionsexample.spring_transactions.dto.AccountResponseDTO;
import com.transactionsexample.spring_transactions.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        accountService.createAccount(accountRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        AccountResponseDTO account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByCustomerId(@PathVariable Long customerId) {
        List<AccountResponseDTO> accounts = accountService.getAccountsByCustomerId(customerId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok().build();
    }
}