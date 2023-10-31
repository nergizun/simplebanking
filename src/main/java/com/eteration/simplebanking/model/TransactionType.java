package com.eteration.simplebanking.model;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEPOSIT(1, Category.CREDIT),
    WITHDRAWAL(2, Category.DEBIT),
    BILL_PAYMENT(3, Category.DEBIT),
    TRANSFER_INTO(4, Category.DEBIT),
    TRANSFER_FROM(5, Category.DEBIT);

    private final int value;
    private final Category typeCategory;

    TransactionType(int value, Category typeCategory) {
        this.value = value;
        this.typeCategory = typeCategory;
    }

    public boolean isCategory(Category category) {
        return category == typeCategory;
    }

    public enum Category {
        CREDIT,
        DEBIT
    }
}