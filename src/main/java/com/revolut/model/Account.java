package com.revolut.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account implements Serializable {

    private static final long serialVersionUID = 4021069754075234975L;

    private String userId;
    private String accountId;
    private double amount;
    private Currency currency = Currency.GBP;
    private LocalDateTime lastTransactionDate;
    private LocalDateTime createDate = LocalDateTime.now();
    private AccountType accountType;
    private AccountSubType accountSubType;
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    /**
     * Instantiates a new Account.
     */
    public Account() {
    }

    /**
     * Instantiates a new Account.
     *
     * @param accountId the account id
     * @param userId    the user id
     */
    public Account(String accountId, String userId) {
        this.accountId = accountId;
        this.userId = userId;
    }

    /**
     * Gets account id.
     *
     * @return the account id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets account id.
     *
     * @param accountId the account id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets last transaction date.
     *
     * @return the last transaction date
     */
    public LocalDateTime getLastTransactionDate() {
        return lastTransactionDate;
    }

    /**
     * Sets last transaction date.
     *
     * @param lastTransactionDate the last transaction date
     */
    public void setLastTransactionDate(LocalDateTime lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets account type.
     *
     * @return the account type
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Sets account type.
     *
     * @param accountType the account type
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets account sub type.
     *
     * @return the account sub type
     */
    public AccountSubType getAccountSubType() {
        return accountSubType;
    }

    /**
     * Sets account sub type.
     *
     * @param accountSubType the account sub type
     */
    public void setAccountSubType(AccountSubType accountSubType) {
        this.accountSubType = accountSubType;
    }

    /**
     * Gets account status.
     *
     * @return the account status
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets account status.
     *
     * @param accountStatus the account status
     */
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
