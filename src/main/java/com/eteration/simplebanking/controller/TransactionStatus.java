package com.eteration.simplebanking.controller;


// This class is a placeholder you can change the complete implementation

import com.fasterxml.jackson.annotation.JsonInclude;


import javax.persistence.Entity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionStatus {
    private String status;
    private String approvalCode;

    public TransactionStatus(String status) {
        this.status = status;
    }

    public TransactionStatus(String status, String approvalCode) {
        this.status = status;
        this.approvalCode = approvalCode;
    }

    // Getters and setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}

