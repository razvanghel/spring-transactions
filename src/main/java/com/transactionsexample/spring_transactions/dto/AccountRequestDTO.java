package com.transactionsexample.spring_transactions.dto;

public class AccountRequestDTO {
    private Long customerId;
    private Double balance;

    // Constructors, getters, setters, etc.

    public AccountRequestDTO() {}

    public AccountRequestDTO(Long customerId, Double balance) {
        this.customerId = customerId;
        this.balance = balance;
    }

    // Getters and setters

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
