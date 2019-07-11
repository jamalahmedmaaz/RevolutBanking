package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.Account;

/**
 * The interface Account service.
 */
public interface AccountService {
    /**
     * Gets balance.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the balance
     */
    double getBalance(BankingRequestDTO bankingRequestDTO);

    /**
     * Create account account.
     *
     * @param account the account
     * @return the account
     */
    Account createAccount(Account account);

    /**
     * Update account account.
     *
     * @param account the account
     * @return the account
     */
    Account updateAccount(Account account);

    /**
     * Delete account.
     *
     * @param account the account
     */
    void deleteAccount(Account account);

    /**
     * Gets account.
     *
     * @param account the account
     * @return the account
     */
    Account getAccount(Account account);
}
