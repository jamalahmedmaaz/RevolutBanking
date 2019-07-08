package com.revolut.services;
import com.revolut.dtos.BankingRequest;
import com.revolut.model.Account;

public interface AccountService {
    double getBalance(BankingRequest bankingRequest);

    Account createAccount(Account account);
}
