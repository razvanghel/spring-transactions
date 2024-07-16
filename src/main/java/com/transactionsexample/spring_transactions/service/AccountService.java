package com.transactionsexample.spring_transactions.service;


import com.transactionsexample.spring_transactions.Utils;
import com.transactionsexample.spring_transactions.dto.AccountDTO;
import com.transactionsexample.spring_transactions.dto.TransactionDTO;
import com.transactionsexample.spring_transactions.exceptions.AccountNotFoundException;
import com.transactionsexample.spring_transactions.exceptions.InvalidBalanceException;
import com.transactionsexample.spring_transactions.repository_impl.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepositoryImpl accountRepository;

    @Autowired
    private TransactionService transactionService;


    public void createAccount(AccountDTO accountDTO) {

        //if balance is bigger than 0 -> create account for user
        if(accountDTO.getBalance()<0){
            throw new InvalidBalanceException("Initial balance cannot be smaller than 0");
        }else{
            //set an ID
            accountDTO.setId(Utils.generateRandomId());
        }
        if(accountDTO.getBalance() > 0){
            TransactionDTO transaction = new TransactionDTO();
            transaction.setAccountId(accountDTO.getId());
            transaction.setAmount(accountDTO.getBalance());
            transactionService.createTransaction(transaction);
        }

        accountRepository.save(accountDTO);
    }

    public AccountDTO getAccountById(Long id) {
        AccountDTO account = accountRepository.findById(id);
        if(account == null){
            throw new AccountNotFoundException("Account not found");
        }
        return computeBalanceOfAccount(account);
    }

    public List<AccountDTO> getAccountsByCustomerId(Long customerId) {
        List<AccountDTO> accounts = accountRepository.findByCustomerId(customerId);
        accounts.forEach(this::computeBalanceOfAccount);
        return accounts;
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccountById(Long id) {
        if(!accountRepository.existsById(id)){
            throw new AccountNotFoundException("No accounts associated with id: "+id);
        }else{
            List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(id);
            transactions.forEach(transaction -> transactionService.deleteTransactionById(transaction.getId()));
        }
        accountRepository.deleteById(id);
    }

    private AccountDTO computeBalanceOfAccount(AccountDTO account) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(account.getId());
        account.setBalance(transactions.stream()
                .mapToDouble(TransactionDTO::getAmount)
                .sum());
        return account;
    }
}