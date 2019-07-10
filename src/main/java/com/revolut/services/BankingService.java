package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;

/**
 * The interface Banking service.
 */
public interface BankingService {
    /**
     * Credit money into account string.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the string
     */
    String creditMoneyIntoAccount(BankingRequestDTO bankingRequestDTO);

    /**
     * Debit money from account string.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the string
     */
    String debitMoneyFromAccount(BankingRequestDTO bankingRequestDTO);

    /**
     * View balance of account double.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the double
     */
    double viewBalanceOfAccount(BankingRequestDTO bankingRequestDTO);

    /**
     * Check if account exists boolean.
     *
     * @param accountId the account id
     * @return the boolean
     */
    boolean checkIfAccountExists(String accountId);

    /**
     * Transfer money from one account to another string.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the string
     */
    String transferMoneyFromOneAccountToAnother(BankingRequestDTO bankingRequestDTO);
}
