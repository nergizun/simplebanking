package com.eteration.simplebanking.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.eteration.simplebanking.dto.TransactionDto;
import lombok.Data;
import lombok.NoArgsConstructor;

// This class is a placeholder you can change the complete implementation
@Entity
public class DepositTransaction extends Transaction {
    public DepositTransaction( double amount) {
        super( amount);
    }

    public DepositTransaction( TransactionDto dto) {
        super(dto);
    }

    public DepositTransaction() {

    }

    @Override
    public void post(Account account) {
        account.deposit(this.getAmount());
    }
}
