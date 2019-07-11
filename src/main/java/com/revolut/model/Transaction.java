package com.revolut.model;

import java.time.LocalDateTime;

/**
 * The type Transaction.
 */
public class Transaction {

    private String transactionId;
    private TransactionType transactionType;
    private String destinationAccountId;
    private String sourceAccountId;
    private Double amount;
    private LocalDateTime createdTime = LocalDateTime.now();
    private LocalDateTime transactionTime;
    private TransactionStatus transactionStatus = TransactionStatus.NEW;
    private String message;

    /**
     * Instantiates a new Transaction.
     *
     * @param transactionId        the transaction id
     * @param transactionType      the transaction type
     * @param sourceAccountId      the source account id
     * @param destinationAccountId the destination account id
     * @param amount               the amount
     * @param transactionTime      the transaction time
     * @param transactionStatus    the transaction status
     */
    public Transaction(String transactionId, TransactionType transactionType,
                       String sourceAccountId, String destinationAccountId,
                       double amount, LocalDateTime transactionTime,
                       TransactionStatus transactionStatus) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.destinationAccountId = destinationAccountId;
        this.sourceAccountId = sourceAccountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
        this.transactionStatus = transactionStatus;
    }

    /**
     * Instantiates a new Transaction.
     *
     * @param transactionId     the transaction id
     * @param transactionType   the transaction type
     * @param sourceAccountId   the source account id
     * @param amount            the amount
     * @param transactionTime   the transaction time
     * @param transactionStatus the transaction status
     */
    public Transaction(String transactionId, TransactionType transactionType,
                       String sourceAccountId, double amount,
                       LocalDateTime transactionTime,
                       TransactionStatus transactionStatus) {

        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.sourceAccountId = sourceAccountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
        this.transactionStatus = transactionStatus;
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
     * Gets destination account id.
     *
     * @return the destination account id
     */
    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    /**
     * Sets destination account id.
     *
     * @param destinationAccountId the destination account id
     */
    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
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
     * Gets source account id.
     *
     * @return the source account id
     */
    public String getSourceAccountId() {
        return sourceAccountId;
    }

    /**
     * Sets source account id.
     *
     * @param sourceAccountId the source account id
     */
    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    /**
     * Gets transaction status.
     *
     * @return the transaction status
     */
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * Sets transaction status.
     *
     * @param transactionStatus the transaction status
     */
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
