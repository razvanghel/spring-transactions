package com.transactionsexample.spring_transactions.model;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Double balance;

    // Constructors, getters, setters, etc.

    public Account() {
        this.balance = 0.0;
    }

    public Account(Long customerId) {
        this.customerId = customerId;
        this.balance = 0.0;
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