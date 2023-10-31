package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.model.TransactionType;
import com.eteration.simplebanking.validation.ValidTransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {
    @ValidTransactionType
    private TransactionType transactionType;

    @NotNull
    private double amount;
    private String payee;

    private String approvalCode;

    public TransactionDto(TransactionType transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public TransactionDto() {
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getPayee() {
        return payee;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
