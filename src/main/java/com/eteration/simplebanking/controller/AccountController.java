package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.controller.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.TransactionType;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;

// This class is a placeholder you can change the complete implementation

@RestController
@Validated
@RequestMapping("/account/v1")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String accountNumber) {
        try {
            AccountDto accountDto = accountService.getAccount(accountNumber);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @Valid @RequestBody TransactionDto transactionDto) {
        if (!transactionDto.getTransactionType().isCategory(TransactionType.Category.CREDIT)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransactionStatus("Invalid transaction type for credit endpoint."));
        }
        return accountService.creditTransaction(accountNumber, transactionDto);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @Valid @RequestBody TransactionDto transactionDto) {
        if (!transactionDto.getTransactionType().isCategory(TransactionType.Category.DEBIT)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransactionStatus("Invalid transaction type for debit endpoint."));
        }
        try {
            return accountService.debitTransaction(accountNumber, transactionDto);
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransactionStatus("Insufficient Balance!"));
        }

    }

}