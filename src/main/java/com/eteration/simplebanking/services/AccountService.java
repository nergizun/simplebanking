package com.eteration.simplebanking.services;


import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.controller.exception.InsufficientBalanceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

// This class is a placeholder you can change the complete implementation
@Service
public interface AccountService {
    AccountDto getAccount(String accountNumber) throws AccountNotFoundException;
    ResponseEntity<TransactionStatus> creditTransaction(String accountNumber,TransactionDto TransactionDto) ;
    ResponseEntity<TransactionStatus> debitTransaction(String accountNumber,TransactionDto TransactionDto) throws InsufficientBalanceException;



}
