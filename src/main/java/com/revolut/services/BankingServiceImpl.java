package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.BankingModel;

/**
 * The type Banking service.
 */
public class BankingServiceImpl implements BankingService {

    private static BankingService bankingService;

    private TransactionSystem transactionSystem;
    private BankingModel bankingModel;
    private ValidationService validationService;

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
                if (bankingService == null) {
                    bankingService = new BankingServiceImpl();
                }
            }
        }
        return bankingService;
    }

    @Override
    public String creditMoneyIntoAccount(BankingRequestDTO bankingRequestDTO) {
        validationService.validateIfAccountExists(bankingRequestDTO.getDestinationAccountId());
        return transactionSystem.addMoneyIntoAccount(bankingRequestDTO);
    }

    @Override
    public String debitMoneyFromAccount(BankingRequestDTO bankingRequestDTO) {
        validationService.validateIfAccountExists(bankingRequestDTO.getDestinationAccountId());
        return transactionSystem.addMoneyIntoAccount(bankingRequestDTO);
    }

    @Override
    public double viewBalanceOfAccount(BankingRequestDTO bankingRequestDTO) {
        return bankingModel.getAccountBalance(bankingRequestDTO.getSourceAccountId());
    }

    @Override
    public boolean checkIfAccountExists(String accountId) {
        return bankingModel.doesAccountExists(accountId);
    }

    @Override
    public String transferMoneyFromOneAccountToAnother(BankingRequestDTO bankingRequestDTO) {
        validationService.validateIfAccountHaveSufficientFundsToDebit(bankingRequestDTO);
        return transactionSystem.transferMoneyBetweenAccounts(bankingRequestDTO);
    }
}
