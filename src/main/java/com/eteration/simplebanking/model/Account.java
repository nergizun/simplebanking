package com.eteration.simplebanking.model;

import com.eteration.simplebanking.controller.exception.InsufficientBalanceException;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.TransactionDto;
// This class is a place holder you can change the complete implementation

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    private String accountNumber;
    private double balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
    private List<Transaction> transactions  = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public Account() {

    }


    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        } else {
            throw new InsufficientBalanceException("Insufficient funds for withdrawal!");
        }
    }

    private void addTransaction(Transaction transaction){
        if (transaction != null) {
            transactions.add(transaction);
        }
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.post(this);
        this.addTransaction(transaction);
    }

    public AccountDto mapToAccountDto() {
        AccountDto accountDTO = new AccountDto();
        accountDTO.setOwner(this.owner);
        accountDTO.setAccountNumber(this.accountNumber);
        accountDTO.setBalance(this.balance);
        List<TransactionDto> transactionDTOs = this.transactions.stream()
                .map(Transaction::mapTransactionDto)
                .collect(Collectors.toList());
        accountDTO.setTransactions(transactionDTOs);
        return accountDTO;
    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
