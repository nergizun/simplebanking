package com.eteration.simplebanking.model;


import com.eteration.simplebanking.controller.exception.InsufficientBalanceException;
import com.eteration.simplebanking.dto.TransactionDto;
import com.sun.istack.NotNull;

import javax.persistence.Entity;

// This class is a placeholder you can change the complete implementation
@Entity
public class PhoneBillPaymentTransaction extends Transaction {

    @NotNull
    private String payee;
    public PhoneBillPaymentTransaction(double amount) {
        super( amount);
    }
    public PhoneBillPaymentTransaction(TransactionDto dto) {
        super( dto);
        this.payee = dto.getPayee();
    }

    public PhoneBillPaymentTransaction(double amount, String payee) {
        super(amount);
        this.payee = payee;
    }

    public PhoneBillPaymentTransaction() {

    }

    @Override
    public void post(Account account) throws InsufficientBalanceException {
        account.withdraw(this.getAmount());
    }
}
