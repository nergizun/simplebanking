package com.eteration.simplebanking.util;

import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.model.*;

public class TransactionFactory {
    public static Transaction createTransaction(TransactionDto transactionDto) {
        if (transactionDto.getTransactionType() == TransactionType.DEPOSIT) {
            return new DepositTransaction(transactionDto);
        } else if (transactionDto.getTransactionType() == TransactionType.WITHDRAWAL) {
            return new WithdrawalTransaction(transactionDto);
        } else if (transactionDto.getTransactionType() == TransactionType.BILL_PAYMENT) {
            return new PhoneBillPaymentTransaction(transactionDto);
        }
        throw new IllegalArgumentException("Invalid transaction type: " + transactionDto.getTransactionType());
    }
}
