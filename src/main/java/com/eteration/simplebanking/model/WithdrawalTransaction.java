package com.eteration.simplebanking.model;


import com.eteration.simplebanking.controller.exception.InsufficientBalanceException;
import com.eteration.simplebanking.dto.TransactionDto;

import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction( double amount) {
        super( amount);
    }

    public WithdrawalTransaction( TransactionDto dto) {
        super( dto);
    }


    public WithdrawalTransaction() {

    }

    @Override
    public void post(Account account) throws InsufficientBalanceException {
        account.withdraw(this.getAmount());
    }
}


