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
    void validateIfAccountExists(String accountId);

    /**
     * Validate if account have sufficient funds to debit.
     *
     * @param bankingRequestDTO the banking request dto
     */
    void validateIfAccountHaveSufficientFundsToDebit(BankingRequestDTO bankingRequestDTO);
}
