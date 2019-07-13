package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;

/**
 * The interface Transaction system.
 */
public interface TransactionSystem {

    /**
     * Transfer money between accounts string.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the string
     */
    String transferMoneyBetweenAccounts(BankingRequestDTO bankingRequestDTO);

    /**
     * Add money into account string.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the string
     */
    String addMoneyIntoAccount(BankingRequestDTO bankingRequestDTO);

    /**
     * Deduct money from account string.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the string
     */
    String deduceMoneyFromAccount(BankingRequestDTO bankingRequestDTO);
}
