package com.transactionsexample.spring_transactions.repository;

import com.transactionsexample.spring_transactions.dto.AccountDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository {

    AccountDTO save(AccountDTO account);

    AccountDTO findById(Long id);

    List<AccountDTO> findByCustomerId(Long customerId);

    List<AccountDTO> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);

}