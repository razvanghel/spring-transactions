package com.transactionsexample.spring_transactions.dto;

import java.util.List;
public class AccountResponseDTO {
    private Long id;
    private Long customerId;
    private Double balance;

    // Constructors, getters, setters, etc.

    public AccountResponseDTO() {}

    public AccountResponseDTO(Long id, Long customerId, Double balance) {
        this.id = id;
        this.customerId = customerId;
        this.balance = balance;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
