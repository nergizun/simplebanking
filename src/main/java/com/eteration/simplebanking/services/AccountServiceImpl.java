package com.eteration.simplebanking.services;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.controller.exception.InsufficientBalanceException;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.util.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;


    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public AccountDto getAccount(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findWithTransactionsByAccountNumber(accountNumber);
        if (account != null) {
            return account.mapToAccountDto();
        } else {
            throw new AccountNotFoundException("Account not found for account number: " + accountNumber);
        }
    }

    @Override
    public ResponseEntity<TransactionStatus> creditTransaction(String accountNumber, TransactionDto transactionDto) {
        try {
            return processTransaction(accountNumber, transactionDto);
        } catch (Exception ex) {
            return new ResponseEntity<>(new TransactionStatus("Error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<TransactionStatus> debitTransaction(String accountNumber, TransactionDto transactionDto)
            throws InsufficientBalanceException {

        try {
            return processTransaction(accountNumber, transactionDto);
        }
        catch (InsufficientBalanceException e) {
            return new ResponseEntity<>(new TransactionStatus("Insufficient Balance", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new TransactionStatus("Error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<TransactionStatus> processTransaction(String accountNumber, TransactionDto transactionDto) throws InsufficientBalanceException {
        Account account = findAccount(accountNumber);

        if (account == null) {
            return new ResponseEntity<>(new TransactionStatus("Account not found"), HttpStatus.NOT_FOUND);
        }

        Transaction transaction = TransactionFactory.createTransaction(transactionDto);
        transaction.setAccount(account);
        account.post(transaction);

        // Save the approval code while saving the transaction
        String approvalCode = saveTransactionWithApprovalCode(account, transaction);

        return new ResponseEntity<>(new TransactionStatus("OK", approvalCode), HttpStatus.OK);
    }


    @Transactional
    public String saveTransactionWithApprovalCode(Account account, Transaction transaction) {
        // Generate an approval code (you can customize this logic)
        String approvalCode = generateApprovalCode();

        // Set the approval code on the transaction
        transaction.setApprovalCode(approvalCode);

        // Save the updated account (including the transaction)
        accountRepository.save(account);

        return approvalCode;
    }

    private String generateApprovalCode() {
        return UUID.randomUUID().toString();
    }

}
