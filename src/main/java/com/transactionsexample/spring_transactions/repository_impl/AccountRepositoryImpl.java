package com.transactionsexample.spring_transactions.repository_impl;

import com.transactionsexample.spring_transactions.Utils;
import com.transactionsexample.spring_transactions.dto.AccountDTO;
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
    public AccountDTO save(AccountDTO accountRequestDTO) {
        Account account = new Account();
        account.setId(accountRequestDTO.getId());
        account.setCustomerId(accountRequestDTO.getCustomerId());
        account.setBalance(accountRequestDTO.getBalance());
        accounts.put(account.getId(), account);
        return convertToResponseDTO(account);
    }

    @Override
    public AccountDTO findById(Long id) {
        Account account = accounts.get(id);
        if (account == null) {
            return null;
        }
        return convertToResponseDTO(account);
    }

    @Override
    public List<AccountDTO> findByCustomerId(Long customerId) {
        List<AccountDTO> result = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.getCustomerId().equals(customerId)) {
                result.add(convertToResponseDTO(account));
            }
        }
        return result;
    }

    @Override
    public List<AccountDTO> findAll() {
        List<AccountDTO> result = new ArrayList<>();
        for (Account account : accounts.values()) {
            result.add(convertToResponseDTO(account));
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        accounts.remove(id);
    }

    @Override
    public boolean existsById(Long id){
        return accounts.containsKey(id);
    }

    private AccountDTO convertToResponseDTO(Account account) {
        AccountDTO responseDTO = new AccountDTO();
        responseDTO.setId(account.getId());
        responseDTO.setCustomerId(account.getCustomerId());
        responseDTO.setBalance(account.getBalance());
        return responseDTO;
    }
}
