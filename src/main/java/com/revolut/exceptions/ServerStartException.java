package com.revolut.exceptions;

/**
 * The type Server start exception.
 */
public class ServerStartException extends BankingException {

    private String message;

    @Override
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

    @Override
    public String toString() {
        return "RevolutBankingServer Start Exception: failure reason " + message;
    }
}
