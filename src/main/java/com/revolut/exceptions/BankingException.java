package com.revolut.exceptions;

/**
 * The type Banking exception.
 */
public class BankingException extends RuntimeException {

    private String additionalMessage;
    private int code;

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

    /**
     * Instantiates a new Banking exception.
     *
     * @param bankingValidationCode the validation code
     */
    public BankingException(BankingValidationCode bankingValidationCode) {
        super(bankingValidationCode.getMessage());
        this.code = bankingValidationCode.getCode();
    }

    /**
     * Instantiates a new Banking exception.
     *
     * @param bankingValidationCode the validation code
     * @param additionalMessage     the additional message
     */
    public BankingException(BankingValidationCode bankingValidationCode, String additionalMessage) {
        super(bankingValidationCode.getMessage());
        this.code = bankingValidationCode.getCode();
        this.additionalMessage = additionalMessage;

    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets additional message.
     *
     * @return the additional message
     */
    public String getAdditionalMessage() {
        return additionalMessage;
    }

    /**
     * Sets additional message.
     *
     * @param additionalMessage the additional message
     */
    public void setAdditionalMessage(String additionalMessage) {
        this.additionalMessage = additionalMessage;
    }
}
