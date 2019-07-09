package com.revolut.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Banking request dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankingRequestDTO implements Serializable {
    private static final long serialVersionUID = 8939109958669455523L;
    private String transactionId;
    private String toAccountId;
    private String fromAccountId;
    private double amount;
    private LocalDateTime transactionTime;

    /**
     * Instantiates a new Banking request dto.
     *
     * @param toAccountId     the to account id
     * @param amount          the amount
     * @param transactionTime the transaction time
     */
    public BankingRequestDTO(String toAccountId, double amount
            , LocalDateTime transactionTime) {
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    /**
     * Instantiates a new Banking request dto.
     *
     * @param fromAccountId the from account id
     */
    public BankingRequestDTO(String fromAccountId) {
        this.fromAccountId = fromAccountId;
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
}
