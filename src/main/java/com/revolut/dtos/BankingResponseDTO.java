package com.revolut.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * The type Banking response dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankingResponseDTO implements Serializable {
    private static final long serialVersionUID = 8756492445243105089L;

    private double balance;
    private String transactionId;

    /**
     * Instantiates a new Banking response dto.
     */
    public BankingResponseDTO() {
    }

    /**
     * Instantiates a new Banking response dto.
     *
     * @param transactionId the transaction id
     */
    public BankingResponseDTO(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Instantiates a new Banking response dto.
     *
     * @param balance the balance
     */
    public BankingResponseDTO(double balance) {
        this.balance = balance;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
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
}
