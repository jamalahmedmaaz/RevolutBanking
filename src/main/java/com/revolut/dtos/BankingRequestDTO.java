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
    private String destinationAccountId;
    private String sourceAccountId;
    private double amount;
    private LocalDateTime transactionTime;

    /**
     * Instantiates a new Banking request dto.
     *
     * @param destinationAccountId the destination account id
     * @param amount               the amount
     * @param transactionTime      the transaction time
     */
    public BankingRequestDTO(String destinationAccountId, double amount
            , LocalDateTime transactionTime) {
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    /**
     * Instantiates a new Banking request dto.
     *
     * @param sourceAccountId the source account id
     */
    public BankingRequestDTO(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    /**
     * Instantiates a new Banking request dto.
     */
    public BankingRequestDTO() {

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
