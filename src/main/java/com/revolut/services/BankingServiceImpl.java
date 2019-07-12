package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.BankingModel;
import com.revolut.model.TransactionStatus;

/**
 * The type Banking service.
 */
public class BankingServiceImpl implements BankingService {

    private static BankingService bankingService;

    private TransactionSystem transactionSystem;
    private BankingModel bankingModel;
    private BankingServiceImpl() {
        transactionSystem = TransactionSystemImpl.getTransactionSystem();
        bankingModel = BankingModel.getBankingModel();
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
        return transactionSystem.addMoneyIntoAccount(bankingRequestDTO);
    }

    @Override
    public String debitMoneyFromAccount(BankingRequestDTO bankingRequestDTO) {
        return transactionSystem.deduceMoneyFromAccount(bankingRequestDTO);
    }

    @Override
    public double viewBalanceOfAccount(BankingRequestDTO bankingRequestDTO) {
        return bankingModel.getAccountBalance(bankingRequestDTO.getSourceAccountId());
    }

    @Override
    public String transferMoneyFromSenderToReceiver(BankingRequestDTO bankingRequestDTO) {
        return transactionSystem.transferMoneyBetweenAccounts(bankingRequestDTO);
    }

    @Override
    public TransactionStatus getTransactionStatus(String transactionId) {
        return bankingModel.getTransactionStatus(transactionId);
    }

    @Override
    public void blockAccount(String accountId) {
        bankingModel.blockAccount(accountId);
    }

    @Override
    public void activateAccount(String accountId) {
        bankingModel.activateAccount(accountId);
    }
}
