package com.transactionsexample.spring_transactions.repository;

import com.transactionsexample.spring_transactions.dto.AccountRequestDTO;
import com.transactionsexample.spring_transactions.dto.AccountResponseDTO;
import com.transactionsexample.spring_transactions.model.Account;
import com.transactionsexample.spring_transactions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository {

    void save(AccountRequestDTO account);

    AccountResponseDTO findById(Long id);

    List<AccountResponseDTO> findByCustomerId(Long customerId);

    List<AccountResponseDTO> findAll();

    void deleteById(Long id);
}