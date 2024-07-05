package com.transactionsexample.spring_transactions.service;


import com.transactionsexample.spring_transactions.dto.AccountRequestDTO;
import com.transactionsexample.spring_transactions.dto.AccountResponseDTO;
import com.transactionsexample.spring_transactions.repository_impl.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepositoryImpl accountRepository;

    public void createAccount(AccountRequestDTO accountRequestDTO) {
        accountRepository.save(accountRequestDTO);
    }

    public AccountResponseDTO getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public List<AccountResponseDTO> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }
}