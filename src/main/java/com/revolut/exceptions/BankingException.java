package com.revolut.exceptions;

/**
 * The type Banking exception.
 */
public class BankingException extends RuntimeException {

    /**
     * Instantiates a new Banking exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public BankingException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new Banking exception.
     *
     * @param message the message
     */
    public BankingException(String message) {
        super(message);
    }
}
