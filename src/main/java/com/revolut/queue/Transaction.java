package com.revolut.queue;

import java.time.LocalDateTime;

/**
 * The type Transaction.
 */
public class Transaction {

    private String transactionId;
    private TransactionType transactionType;
    private String toAccountId;
    private String fromAccountId;
    private Double amount;
    private LocalDateTime createdTime = LocalDateTime.now();
    private LocalDateTime transactionTime;

    /**
     * Instantiates a new Transaction.
     *
     * @param transactionId   the transaction id
     * @param transactionType the transaction type
     * @param toAccountId     the to account id
     * @param fromAccountId   the from account id
     * @param amount          the amount
     * @param transactionTime the transaction time
     */
    public Transaction(String transactionId, TransactionType transactionType,
                       String toAccountId, String fromAccountId,
                       double amount, LocalDateTime transactionTime) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.toAccountId = toAccountId;
        this.fromAccountId = fromAccountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    /**
     * Instantiates a new Transaction.
     *
     * @param transactionId   the transaction id
     * @param transactionType the transaction type
     * @param toAccountId     the to account id
     * @param amount          the amount
     * @param transactionTime the transaction time
     */
    public Transaction(String transactionId, TransactionType transactionType,
                       String toAccountId, double amount,
                       LocalDateTime transactionTime) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    /**
     * Gets transaction id.
     *
     * @return the transaction id
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets transaction id.
     *
     * @param transactionId the transaction id
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets transaction type.
     *
     * @return the transaction type
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Sets transaction type.
     *
     * @param transactionType the transaction type
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets to account id.
     *
     * @return the to account id
     */
    public String getToAccountId() {
        return toAccountId;
    }

    /**
     * Sets to account id.
     *
     * @param toAccountId the to account id
     */
    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets created time.
     *
     * @return the created time
     */
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets created time.
     *
     * @param createdTime the created time
     */
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Gets transaction time.
     *
     * @return the transaction time
     */
    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    /**
     * Sets transaction time.
     *
     * @param transactionTime the transaction time
     */
    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    /**
     * Gets from account id.
     *
     * @return the from account id
     */
    public String getFromAccountId() {
        return fromAccountId;
    }

    /**
     * Sets from account id.
     *
     * @param fromAccountId the from account id
     */
    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
}
