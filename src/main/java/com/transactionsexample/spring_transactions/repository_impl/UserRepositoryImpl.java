package com.transactionsexample.spring_transactions.repository_impl;

import com.transactionsexample.spring_transactions.Utils;
import com.transactionsexample.spring_transactions.dto.UserDTO;
import com.transactionsexample.spring_transactions.model.User;
import com.transactionsexample.spring_transactions.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        users.put(user.getId(), user);
        return convertToDTO(user);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = users.get(id);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> result = new ArrayList<>();
        for (User user : users.values()) {
            result.add(convertToDTO(user));
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }

    @Override
    public boolean existsById(Long id){
        return users.containsKey(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        return userDTO;
    }

}
