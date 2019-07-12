package com.revolut.exceptions;

/**
 * The enum Banking validation code.
 */
public enum BankingValidationCode {

    /**
     * Missing account id banking validation code.
     */
    MISSING_ACCOUNT_ID(1000, BankingCodeMessages.B1),
    /**
     * Account doesnt exists banking validation code.
     */
    ACCOUNT_DOESNT_EXISTS(2000, BankingCodeMessages.B2),
    /**
     * Account is not active banking validation code.
     */
    ACCOUNT_IS_NOT_ACTIVE(3000, BankingCodeMessages.B3),
    /**
     * Insufficient account balance banking validation code.
     */
    INSUFFICIENT_ACCOUNT_BALANCE(4000, BankingCodeMessages.B4),
    /**
     * Transaction doesnt exists banking validation code.
     */
    TRANSACTION_DOESNT_EXISTS(5000, BankingCodeMessages.B5),
    /**
     * Sender receiver cannot be same banking validation code.
     */
    SENDER_RECEIVER_CANNOT_BE_SAME(6000, BankingCodeMessages.B6),
    /**
     * Crediting amount cannot be low banking validation code.
     */
    CREDITING_AMOUNT_CANNOT_BE_LOW(7000, BankingCodeMessages.B7),
    /**
     * Debit amount cannot be low banking validation code.
     */
    DEBIT_AMOUNT_CANNOT_BE_LOW(8000, BankingCodeMessages.B8),
    /**
     * Transfer amount cannot be low banking validation code.
     */
    TRANSFER_AMOUNT_CANNOT_BE_LOW(9000, BankingCodeMessages.B9);

    private final int code;
    private final String message;

    BankingValidationCode(int validationCode, String validationMessage) {
        this.code = validationCode;
        this.message = validationMessage;
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
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}

