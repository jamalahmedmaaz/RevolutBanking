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
     * @param amountToDeduce the amount to deduce
     */
    void validateIfSufficientFundExists(String fromAccountId,
                                        double amountToDeduce);
}
