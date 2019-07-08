package com.revolut.services;
import com.revolut.dtos.BankingRequest;

public interface TransactionSystem {

    void transferMoneyBetweenAccounts(BankingRequest bankingRequest);

    void addMoneyIntoAccount(BankingRequest bankingRequest);

    void deduceMoneyFromAccount(BankingRequest bankingRequest);
}
