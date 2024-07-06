package com.transactionsexample.spring_transactions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private Double amount;

    private LocalDateTime timestamp;

    // Getters
    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
