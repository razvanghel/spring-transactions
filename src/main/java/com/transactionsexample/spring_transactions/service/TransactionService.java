package com.transactionsexample.spring_transactions.service;

import com.transactionsexample.spring_transactions.Utils;
import com.transactionsexample.spring_transactions.dto.TransactionDTO;
import com.transactionsexample.spring_transactions.exceptions.InvalidTransactionException;
import com.transactionsexample.spring_transactions.exceptions.TransactionNotFoundException;
import com.transactionsexample.spring_transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(TransactionDTO transactionDTO) {
        if(transactionDTO.getAmount() <= 0){
            throw new InvalidTransactionException("Transaction must have an amount bigger than 0");
        }
        transactionDTO.setId(Utils.generateRandomId());
        transactionRepository.save(transactionDTO);
    }

    public TransactionDTO getTransactionById(Long id) {
        TransactionDTO transaction = transactionRepository.findById(id);
        if(transaction == null)
            throw new TransactionNotFoundException("Transaction not found");
        return transaction;
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransactionById(Long id) {
        if(!transactionRepository.existsById(id)){
            throw new TransactionNotFoundException("No transaction associated with id: "+ id);
        }
        transactionRepository.deleteById(id);
    }

    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
