package com.transactionsexample.spring_transactions.dto;

public class AccountDTO {
    private Long id;
    private Long customerId;
    private Double balance;

    // Constructors, getters, setters, etc.

    public AccountDTO() {}

    public AccountDTO(Long id, Long customerId, Double balance) {
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
