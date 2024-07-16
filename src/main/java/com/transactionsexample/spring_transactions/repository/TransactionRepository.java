package com.transactionsexample.spring_transactions.repository;
import com.transactionsexample.spring_transactions.dto.TransactionDTO;

import java.util.List;

public interface TransactionRepository {

    void save(TransactionDTO transaction);

    TransactionDTO findById(Long id);

    List<TransactionDTO> findAll();

    void deleteById(Long id);

    List<TransactionDTO> findByAccountId(Long accountId);

    boolean existsById(Long id);

}
