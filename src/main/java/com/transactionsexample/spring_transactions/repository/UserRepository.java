package com.transactionsexample.spring_transactions.repository;

import com.transactionsexample.spring_transactions.dto.UserDTO;

import java.util.List;

public interface UserRepository {

    UserDTO save(UserDTO user);

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);
}