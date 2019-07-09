package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;

/**
 * The interface Banking service.
 */
public interface BankingService {
    /**
     * Add money string.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the string
     */
    String addMoney(BankingRequestDTO bankingRequestDTO);

    /**
     * View balance double.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the double
     */
    double viewBalance(BankingRequestDTO bankingRequestDTO);
}
