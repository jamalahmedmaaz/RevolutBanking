package com.revolut.queue;

import java.time.LocalDate;

public class Transaction {

    private String transactionId;
    private TransactionType transactionType;
    private String toAccountId;
    private String fromAccountId;
    private Double amount;
    private LocalDate createdTime = LocalDate.now();
    private LocalDate transactionTime;

    public Transaction(String transactionId, TransactionType transactionType,
                       String toAccountId, String fromAccountId,
                       double amount, LocalDate transactionTime) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.toAccountId = toAccountId;
        this.fromAccountId = fromAccountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public Transaction(String transactionId, TransactionType transactionType,
                       String toAccountId, double amount,
                       LocalDate transactionTime) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDate createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDate getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDate transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
}
