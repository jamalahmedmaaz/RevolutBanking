package com.revolut.api;

import com.revolut.annotations.RevolutApi;
import com.revolut.annotations.RevolutApiPath;
import com.revolut.dtos.BankingRequestDTO;
import com.revolut.dtos.BankingResponseDTO;
import com.revolut.model.TransactionStatus;
import com.revolut.services.BankingService;
import com.revolut.services.BankingServiceImpl;
import com.revolut.services.ValidationService;
import com.revolut.services.ValidationServiceImpl;
import com.revolut.util.JsonUtil;

/**
 * The type Banking rest api.
 */
@RevolutApi
public class BankingRestAPI {

    private final ValidationService validationService;
    private final BankingService bankingService;

    /**
     * Instantiates a new Banking rest api.
     */
    public BankingRestAPI() {
        this.bankingService = BankingServiceImpl.getBankingService();
        this.validationService = ValidationServiceImpl.getValidationService();
    }

    /**
     * Add money banking response dto.
     *
     * @param requestObject the request object
     * @return the banking response dto
     */
    @RevolutApiPath
    public BankingResponseDTO creditMoney(String requestObject) {
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        validationService.validateIfAccountExists(bankingRequestDTO.getDestinationAccountId());
        String transactionId =
                bankingService.creditMoneyIntoAccount(bankingRequestDTO);
        return new BankingResponseDTO(transactionId);
    }

    /**
     * Debit money banking response dto.
     *
     * @param requestObject the request object
     * @return the banking response dto
     */
    @RevolutApiPath
    public BankingResponseDTO debitMoney(String requestObject) {
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        validationService.validateIfSufficientFundExists(bankingRequestDTO);
        String transactionId =
                bankingService.debitMoneyFromAccount(bankingRequestDTO);
        return new BankingResponseDTO(transactionId);
    }

    /**
     * Transfer money banking response dto.
     *
     * @param requestObject the request object
     * @return the banking response dto
     */
    @RevolutApiPath
    public BankingResponseDTO transferMoney(String requestObject) {
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        validationService.validateIfAccountHaveSufficientFundsToDebit(bankingRequestDTO);
        String transactionId =
                bankingService.transferMoneyFromOneAccountToAnother(bankingRequestDTO);
        return new BankingResponseDTO(transactionId);
    }

    /**
     * View balance banking response dto.
     *
     * @param requestObject the request object
     * @return the banking response dto
     */
    @RevolutApiPath
    public BankingResponseDTO viewBalance(String requestObject) {
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        validationService.validateIfAccountExists(bankingRequestDTO.getDestinationAccountId());
        double balance = bankingService.viewBalanceOfAccount(bankingRequestDTO);
        return new BankingResponseDTO(balance);
    }

    /**
     * Transaction status banking response dto.
     *
     * @param requestObject the request object
     * @return the banking response dto
     */
    @RevolutApiPath
    public BankingResponseDTO transactionStatus(String requestObject) {
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        validationService.validateTransactionId(bankingRequestDTO.getTransactionId());
        TransactionStatus transactionStatus =
                bankingService.getTransactionStatus(bankingRequestDTO.getTransactionId());
        return new BankingResponseDTO(transactionStatus);
    }

}
