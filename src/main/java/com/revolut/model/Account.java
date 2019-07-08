package com.revolut.model;
import java.time.LocalDate;

public class Account {

    private String userId;
    private String accountId;
    private double amount;
    private Currency currency = Currency.GBP;
    private LocalDate lastTransactionDate;
    private LocalDate createDate = LocalDate.now();
    private AccountType accountType;
    private AccountSubType accountSubType;

    public Account(String accountId, String userId) {
        this.accountId = accountId;
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getLastTransactionDate() {
        return lastTransactionDate;
    }

    public void setLastTransactionDate(LocalDate lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountSubType getAccountSubType() {
        return accountSubType;
    }

    public void setAccountSubType(AccountSubType accountSubType) {
        this.accountSubType = accountSubType;
    }
}
