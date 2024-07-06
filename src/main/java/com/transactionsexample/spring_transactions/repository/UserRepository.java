package com.transactionsexample.spring_transactions.repository;

import com.transactionsexample.spring_transactions.dto.UserDTO;

import java.util.List;

public interface UserRepository {

    void save(UserDTO user);

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    void deleteById(Long id);
}