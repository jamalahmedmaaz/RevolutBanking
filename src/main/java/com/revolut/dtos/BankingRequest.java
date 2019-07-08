package com.revolut.dtos;
import java.time.LocalDate;

public class BankingRequest {
    private String transactionId;
    private String toAccountId;
    private String fromAccountId;
    private double amount;
    private LocalDate transactionTime;

    public BankingRequest(String toAccountId, double amount
            , LocalDate transactionTime) {
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public BankingRequest(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDate transactionTime) {
        this.transactionTime = transactionTime;
    }
}
