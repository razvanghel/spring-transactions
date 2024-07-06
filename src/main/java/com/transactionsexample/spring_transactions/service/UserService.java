package com.transactionsexample.spring_transactions.service;

import com.transactionsexample.spring_transactions.dto.UserDTO;
import com.transactionsexample.spring_transactions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        userRepository.save(userDTO);
        return userDTO;
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}