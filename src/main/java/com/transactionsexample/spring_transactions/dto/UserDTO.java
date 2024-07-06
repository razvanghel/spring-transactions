package com.transactionsexample.spring_transactions.dto;

import java.util.List;

public class UserDTO {
    private String name;
    private String surname;
    private Double balance;
    private Long id;
    private List<AccountResponseDTO> accounts;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<AccountResponseDTO> getAccountsResponse() {
        return accounts;
    }

    public void setAccountsResponse(List<AccountResponseDTO> accounts) {
        this.accounts = accounts;
    }
}
