package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.TransactionStatus;

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
     * Transfer money from sender to receiver string.
     *
     * @param bankingRequestDTO the banking request dto
     * @return the string
     */
    String transferMoneyFromSenderToReceiver(BankingRequestDTO bankingRequestDTO);

    /**
     * Gets transaction status.
     *
     * @param transactionId the transaction id
     * @return the transaction status
     */
    TransactionStatus getTransactionStatus(String transactionId);

    /**
     * Block account.
     *
     * @param accountId the source account id
     */
    void blockAccount(String accountId);

    /**
     * Unblock account.
     *
     * @param accountId the source account id
     */
    void activateAccount(String accountId);
}
