package com.transactionsexample.spring_transactions.service;
import com.transactionsexample.spring_transactions.Utils;
import com.transactionsexample.spring_transactions.dto.AccountDTO;

import com.transactionsexample.spring_transactions.dto.TransactionDTO;
import com.transactionsexample.spring_transactions.dto.UserDTO;
import com.transactionsexample.spring_transactions.exceptions.AccountNotFoundException;
import com.transactionsexample.spring_transactions.exceptions.UserNotFoundException;
import com.transactionsexample.spring_transactions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setId(Utils.generateRandomId());
        return userRepository.save(userDTO);
    }

    public UserDTO getUserById(Long id) {
        UserDTO user = userRepository.findById(id);
        if(user == null)
            throw new UserNotFoundException("User not found");
        return computeTotalBalanceOfUser(user);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userRepository.findAll();
        users.forEach(this::computeTotalBalanceOfUser);
        return users;
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }else{
            List<AccountDTO> accounts = accountService.getAccountsByCustomerId(id);
            accounts.forEach(account -> accountService.deleteAccountById(account.getId()));
        }
        userRepository.deleteById(id);
    }

    private UserDTO computeTotalBalanceOfUser(UserDTO user){
        List<AccountDTO> accounts = accountService.getAccountsByCustomerId(user.getId());
        user.setBalance(accounts.stream()
                .mapToDouble(AccountDTO::getBalance)
                .sum());
        return user;
    }
}