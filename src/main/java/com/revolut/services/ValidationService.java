package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
/**
 * The interface Validation service.
 */
public interface ValidationService {
    /**
     * Validate if account exists.
     *
     * @param accountId the account id
     */
    void validateAccount(String accountId);

    /**
     * Validate if account have sufficient funds to debit.
     *
     * @param bankingRequestDTO the banking request dto
     */
    void validateIfAccountHaveSufficientFundsToDebit(BankingRequestDTO bankingRequestDTO);

    /**
     * Validate in active account.
     *
     * @param accountId the account id
     */
    void validateInActiveAccount(String accountId);

    /**
     * Validate if sufficient fund exists.
     *
     * @param bankingRequestDTO the banking request dto
     */
    void validateIfSufficientFundExists(BankingRequestDTO bankingRequestDTO);

    /**
     * Validate transaction id.
     *
     * @param transactionId the transaction id
     */
    void validateTransactionId(String transactionId);

    /**
     * Validate if sufficient fund exists.
     *
     * @param fromAccountId  the from account id
     * @param amountToDeduct the amount to deduce
     */
    void validateIfSufficientFundExists(String fromAccountId,
                                        double amountToDeduct);

    /**
     * Validate credit request.
     *
     * @param accountId the account id
     * @param amount    the amount
     */
    void validateCreditRequest(String accountId, double amount);

    /**
     * Validate debit.
     *
     * @param bankingRequestDTO the banking request dto
     */
    void validateDebit(BankingRequestDTO bankingRequestDTO);

    /**
     * Validate transfer.
     *
     * @param bankingRequestDTO the banking request dto
     */
    void validateTransfer(BankingRequestDTO bankingRequestDTO);
}
