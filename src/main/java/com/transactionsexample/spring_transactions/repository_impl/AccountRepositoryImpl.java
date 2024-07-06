package com.transactionsexample.spring_transactions.repository_impl;

import com.transactionsexample.spring_transactions.dto.AccountRequestDTO;
import com.transactionsexample.spring_transactions.dto.AccountResponseDTO;
import com.transactionsexample.spring_transactions.model.Account;
import com.transactionsexample.spring_transactions.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final Map<Long, Account> accounts = new HashMap<>();

    @Override
    public void save(AccountRequestDTO accountRequestDTO) {
        Account account = new Account();
        account.setId(accountRequestDTO.getCustomerId());
        account.setCustomerId(accountRequestDTO.getCustomerId());
        account.setBalance(accountRequestDTO.getBalance());
        // Add other fields as needed
        accounts.put(account.getId(), account);
    }

    @Override
    public AccountResponseDTO findById(Long id) {
        Account account = accounts.get(id);
        if (account == null) {
            return null;
        }
        return convertToResponseDTO(account);
    }

    @Override
    public List<AccountResponseDTO> findByCustomerId(Long customerId) {
        List<AccountResponseDTO> result = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.getCustomerId().equals(customerId)) {
                result.add(convertToResponseDTO(account));
            }
        }
        return result;
    }

    @Override
    public List<AccountResponseDTO> findAll() {
        List<AccountResponseDTO> result = new ArrayList<>();
        for (Account account : accounts.values()) {
            result.add(convertToResponseDTO(account));
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        accounts.remove(id);
    }

    private AccountResponseDTO convertToResponseDTO(Account account) {
        AccountResponseDTO responseDTO = new AccountResponseDTO();
        responseDTO.setId(account.getId());
        responseDTO.setCustomerId(account.getCustomerId());
        responseDTO.setBalance(account.getBalance());
        return responseDTO;
    }
}
