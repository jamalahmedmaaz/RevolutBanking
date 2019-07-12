package com.revolut.services;

import com.revolut.dtos.BankingRequestDTO;
import com.revolut.model.Account;
import com.revolut.model.BankingModel;

/**
 * The type Account service.
 */
public class AccountServiceImpl implements AccountService {

    private static AccountService accountService;
    private final BankingModel bankingModel;

    private AccountServiceImpl() {
        this.bankingModel = BankingModel.getBankingModel();
    }

    /**
     * Gets account service.
     *
     * @return the account service
     */
    public static AccountService getAccountService() {
        if (accountService == null) {
            synchronized (AccountServiceImpl.class) {
                accountService = new AccountServiceImpl();
            }
        }
        return accountService;
    }

    @Override
    public double getBalance(BankingRequestDTO bankingRequestDTO) {
        return bankingModel.getAccountBalance(bankingRequestDTO.getSourceAccountId());
    }

    @Override
    public Account createAccount(Account account) {
        return bankingModel.createAccount(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return bankingModel.updateAccount(account);
    }

    @Override
    public void deleteAccount(Account account) {
        bankingModel.deleteAccount(account.getAccountId());
    }

    @Override
    public Account getAccount(Account account) {
        return bankingModel.getAccount(account.getAccountId());
    }
}
