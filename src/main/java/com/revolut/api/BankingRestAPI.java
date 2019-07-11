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
     * Credit money banking response dto.
     *
     * @param requestObject the request object
     * @return the banking response dto
     */
    @RevolutApiPath
    public BankingResponseDTO creditMoney(String requestObject) {
        System.out.println("[BANKING_CREDIT_REQUEST] credit account called " + requestObject);
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        validationService.validateIfAccountExists(bankingRequestDTO.getSourceAccountId());
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
        System.out.println("[BANKING_DEBIT_REQUEST] debit account called " + requestObject);
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
        System.out.println("[BANKING_TRANSFER_REQUEST] transfer request" + requestObject);
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        validationService.validateIfAccountHaveSufficientFundsToDebit(bankingRequestDTO);
        String transactionId =
                bankingService.transferMoneyFromSenderToReceiver(bankingRequestDTO);
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
        System.out.println("[BANKING_BALANCE_REQUEST] viewBalance" +
                " called " + requestObject);
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        validationService.validateIfAccountExists(bankingRequestDTO.getSourceAccountId());
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
        System.out.println("[BANKING_TRANSACTION_STATUS_REQUEST] transaction " +
                "status called " + requestObject);
        BankingRequestDTO bankingRequestDTO = JsonUtil.readObject(requestObject,
                BankingRequestDTO.class);
        validationService.validateTransactionId(bankingRequestDTO.getTransactionId());
        TransactionStatus transactionStatus =
                bankingService.getTransactionStatus(bankingRequestDTO.getTransactionId());
        return new BankingResponseDTO(transactionStatus);
    }

}
