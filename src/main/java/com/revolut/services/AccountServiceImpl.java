package com.revolut.services;
import com.revolut.dtos.BankingRequest;
import com.revolut.model.Account;
import com.revolut.model.BankingModel;

public class AccountServiceImpl implements AccountService {

    private BankingModel bankingModel = BankingModel.getBankingModel();

    @Override
    public double getBalance(BankingRequest bankingRequest) {
        return bankingModel.getAccountBalance(bankingRequest.getFromAccountId());
    }

    @Override
    public Account createAccount(Account account) {
        return bankingModel.createAccount(account);
    }
}
