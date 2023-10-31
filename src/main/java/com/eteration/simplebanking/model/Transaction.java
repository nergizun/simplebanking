package com.eteration.simplebanking.model;

import javax.persistence.*;
import java.util.Date;

import com.eteration.simplebanking.controller.exception.InsufficientBalanceException;
import com.eteration.simplebanking.dto.TransactionDto;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String approvalCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(double amount) {
        this.amount = amount;
        this.date = new Date();
    }

    public Transaction(TransactionDto dto) {
        this.amount = dto.getAmount();
        this.transactionType = dto.getTransactionType();
        this.date = new Date();
    }
    public Transaction() {}

    abstract void post(Account account) throws InsufficientBalanceException;

    public TransactionDto mapTransactionDto() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(this.amount);
        transactionDto.setTransactionType(this.transactionType);
        transactionDto.setApprovalCode(this.approvalCode);

        return transactionDto;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
