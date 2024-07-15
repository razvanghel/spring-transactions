package com.transactionsexample.spring_transactions.repository_impl;

import com.transactionsexample.spring_transactions.Utils;
import com.transactionsexample.spring_transactions.dto.TransactionDTO;
import com.transactionsexample.spring_transactions.model.Transaction;
import com.transactionsexample.spring_transactions.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final Map<Long, Transaction> transactions = new HashMap<>();

    @Override
    public void save(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(Utils.generateRandomId());
        transaction.setAccountId(transactionDTO.getAccountId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        transactions.put(transaction.getId(), transaction);
    }

    @Override
    public TransactionDTO findById(Long id) {
        Transaction transaction = transactions.get(id);
        if (transaction == null) {
            return null;
        }
        return convertToDTO(transaction);
    }

    @Override
    public List<TransactionDTO> findAll() {
        List<TransactionDTO> result = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            result.add(convertToDTO(transaction));
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        transactions.remove(id);
    }

    @Override
    public List<TransactionDTO> findByAccountId(Long accountId) {
        List<TransactionDTO> result = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getAccountId().equals(accountId)) {
                result.add(convertToDTO(transaction));
            }
        }
        return result;
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAccountId(transaction.getAccountId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setTimestamp(transaction.getTimestamp());
        return transactionDTO;
    }
}
