package com.revolut.exceptions;

public class ServerStartException extends BankingException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Server Start Exception: failure reason " + message;
    }
}
