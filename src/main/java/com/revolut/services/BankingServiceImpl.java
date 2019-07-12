package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.BankingModel;
import com.revolut.model.TransactionStatus;

/**
 * The type Banking service.
 */
public class BankingServiceImpl implements BankingService {

    private static BankingService bankingService;

    private final ValidationService validationService;
    private final TransactionSystem transactionSystem;
    private final BankingModel bankingModel;
    private BankingServiceImpl() {
        transactionSystem = TransactionSystemImpl.getTransactionSystem();
        bankingModel = BankingModel.getBankingModel();
        validationService = ValidationServiceImpl.getValidationService();
    }

    /**
     * Gets banking service.
     *
     * @return the banking service
     */
    public static BankingService getBankingService() {
        if (bankingService == null) {
            synchronized (BankingServiceImpl.class) {
                bankingService = new BankingServiceImpl();
            }
        }
        return bankingService;
    }

    @Override
    public String creditMoneyIntoAccount(BankingRequestDTO bankingRequestDTO) {
        validationService.validateCreditRequest(bankingRequestDTO.getSourceAccountId(), bankingRequestDTO.getAmount());
        return transactionSystem.addMoneyIntoAccount(bankingRequestDTO);
    }

    @Override
    public String debitMoneyFromAccount(BankingRequestDTO bankingRequestDTO) {
        validationService.validateDebit(bankingRequestDTO);
        return transactionSystem.deduceMoneyFromAccount(bankingRequestDTO);
    }

    @Override
    public double viewBalanceOfAccount(BankingRequestDTO bankingRequestDTO) {
        validationService.validateAccount(bankingRequestDTO.getSourceAccountId());
        return bankingModel.getAccountBalance(bankingRequestDTO.getSourceAccountId());
    }

    @Override
    public String transferMoneyFromSenderToReceiver(BankingRequestDTO bankingRequestDTO) {
        validationService.validateTransfer(bankingRequestDTO);
        return transactionSystem.transferMoneyBetweenAccounts(bankingRequestDTO);
    }

    @Override
    public TransactionStatus getTransactionStatus(String transactionId) {
        validationService.validateTransactionId(transactionId);
        return bankingModel.getTransactionStatus(transactionId);
    }

    @Override
    public void blockAccount(String accountId) {
        validationService.validateAccount(accountId);
        bankingModel.blockAccount(accountId);
    }

    @Override
    public void activateAccount(String accountId) {
        validationService.validateInActiveAccount(accountId);
        bankingModel.activateAccount(accountId);
    }
}
