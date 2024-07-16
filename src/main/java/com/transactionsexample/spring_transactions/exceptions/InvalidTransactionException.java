package com.transactionsexample.spring_transactions.exceptions;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        super(message);
    }
}